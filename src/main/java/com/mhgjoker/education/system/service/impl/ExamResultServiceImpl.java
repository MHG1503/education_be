package com.mhgjoker.education.system.service.impl;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.dto.request.user_answer.UserAnswerRequest;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultLazyResponse;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultResponse;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.UserAnswerEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.mapper.ExamResultMapper;
import com.mhgjoker.education.system.repository.ExamRepository;
import com.mhgjoker.education.system.repository.ExamResultRepository;
import com.mhgjoker.education.system.repository.OptionRepository;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final ExamResultMapper examResultMapper;

    @Override
    public PaginatedResponse<ExamResultLazyResponse> list(Integer pageNum, Integer pageSize) {
        Pageable pageable =  PageRequest.of(pageNum, pageSize);
        var rs = examResultRepository.findAll(pageable);

        return new PaginatedResponse<>(
                rs.getContent().stream().map(examResultMapper::entityToLazyResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public PaginatedResponse<ExamResultLazyResponse> listByUserId(Long userId, Integer pageNum, Integer pageSize) {
        Pageable pageable =  PageRequest.of(pageNum, pageSize);
        var rs = examResultRepository.findByUserId(userId, pageable);

        return new PaginatedResponse<>(
                rs.getContent().stream().map(examResultMapper::entityToLazyResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public ExamResultResponse detail(Long id) {
        return examResultMapper.entityToResponse(examResultRepository
                .findById(id, NamedEntityGraph.fetching("exam_result_with_user_answers"))
                .orElse(null));
    }

    @Override
    public ExamResultResponse saveOrUpdate(ExamResultEntity examEntity) {
        return examResultMapper.entityToResponse(examResultRepository.save(examEntity));
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if(examResultRepository.existsById(id)){
            examResultRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public void submitExam(UserEntity user, ExamResultRequest request) {
        ExamResultEntity examResult = new ExamResultEntity();
        var exam = examRepository
                .findById(request.examId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay thong tin bai kiem tra!"));

        examResult.setExam(exam);
        examResult.setUser(user);
        examResult.setStartedAt(request.getStartedAt());
        examResult.setFinishedAt(request.getFinishedAt());

        List<UserAnswerRequest> userAnswerRequests = request.getAnswerRequests();
        Double totalMark = 0.0;
        for(var answer : userAnswerRequests){
            var question = questionRepository.findById(answer.questionId).orElse(null);
            var selectedOption = optionRepository.findById(answer.selectedOptionId).orElse(null);
            if(question == null || selectedOption == null){
                continue;
            }
            UserAnswerEntity userAnswer = new UserAnswerEntity();
            userAnswer.setExamResult(examResult);
            userAnswer.setQuestion(question);
            userAnswer.setSelectedOption(selectedOption);
            userAnswer.setIsCorrect(selectedOption.getIsCorrect());

            if(userAnswer.getIsCorrect()){
                var mark = question.getMark();
                totalMark += mark;
            }
            if(examResult.getUserAnswers() == null){
                examResult.setUserAnswers(new ArrayList<>());
            }
            examResult.getUserAnswers().add(userAnswer);
        }
        examResult.setScore(totalMark);

        examResultRepository.save(examResult);
    }

    @Override
    public ExamResultResponse detailByUserIdAndExamId(Long userId, Long examId) {
        return examResultMapper.entityToResponse(examResultRepository
                .findByUserIdAndExamId(userId, examId, NamedEntityGraph.fetching("exam_result_with_user_answers"))
                .orElse(null));
    }
}
