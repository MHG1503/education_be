package com.mhgjoker.education.system.service.impl;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.dto.request.user_answer.UserAnswerRequest;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultLazyResponse;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultResponse;
import com.mhgjoker.education.system.entity.*;
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

import java.util.*;
import java.util.stream.Collectors;

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
        var exam = examRepository.findById(request.examId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài kiểm tra!"));

        examResult.setExam(exam);
        examResult.setUser(user);
        examResult.setStartedAt(request.getStartedAt());
        examResult.setFinishedAt(request.getFinishedAt());
        examResult.setUserAnswers(new ArrayList<>());

        Set<QuestionEntity> examQuestions = exam.getQuestions();

        Map<Long, QuestionEntity> questionMap = examQuestions.stream()
                .collect(Collectors.toMap(QuestionEntity::getId, q -> q));

        double totalExamMark = examQuestions.stream()
                .mapToDouble(QuestionEntity::getMark)
                .sum();

        double userMark = 0.0;
        Set<Long> answeredSet = new HashSet<>();

        for (var answer : request.getAnswerRequests()) {

            if (!answeredSet.add(answer.questionId)) continue;

            var question = questionMap.get(answer.questionId);
            if (question == null) continue;

            var selectedOption = optionRepository.findById(answer.selectedOptionId).orElse(null);
            if (selectedOption == null) continue;

            if (!selectedOption.getQuestion().getId().equals(question.getId())) continue;

            UserAnswerEntity userAnswer = new UserAnswerEntity();
            userAnswer.setExamResult(examResult);
            userAnswer.setQuestion(question);
            userAnswer.setSelectedOption(selectedOption);
            userAnswer.setIsCorrect(selectedOption.getIsCorrect());

            if (Boolean.TRUE.equals(selectedOption.getIsCorrect())) {
                userMark += question.getMark();
            }

            examResult.getUserAnswers().add(userAnswer);
        }

        double finalScore = (userMark / totalExamMark) * 10.0;

        examResult.setScore(finalScore);
        examResultRepository.save(examResult);
    }

    @Override
    public ExamResultResponse detailByUserIdAndExamId(Long userId, Long examId) {
        return examResultMapper.entityToResponse(examResultRepository
                .findByUserIdAndExamId(userId, examId, NamedEntityGraph.fetching("exam_result_with_user_answers"))
                .orElse(null));
    }
}
