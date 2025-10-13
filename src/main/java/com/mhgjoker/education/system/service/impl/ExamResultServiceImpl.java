package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.dto.request.user_answer.UserAnswerRequest;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import com.mhgjoker.education.system.entity.UserAnswerEntity;
import com.mhgjoker.education.system.repository.ExamRepository;
import com.mhgjoker.education.system.repository.ExamResultRepository;
import com.mhgjoker.education.system.repository.OptionRepository;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<ExamResultEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable =  PageRequest.of(pageNum, pageSize);
        return examResultRepository.findAll(pageable);
    }

    @Override
    public ExamResultEntity detail(Long id) {
        return examResultRepository.findById(id).orElse(null);
    }

    @Override
    public ExamResultEntity saveOrUpdate(ExamResultEntity examEntity) {
        return examResultRepository.save(examEntity);
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
    public void submitExam(ExamResultRequest request) {
        ExamResultEntity examResult = new ExamResultEntity();
        var exam = examRepository
                .findById(request.examId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay thong tin bai kiem tra!"));

        examResult.setExam(exam);
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
}
