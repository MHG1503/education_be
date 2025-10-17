package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.exam.AssignQuestionRequest;
import com.mhgjoker.education.system.dto.request.exam.ExamRequest;
import com.mhgjoker.education.system.dto.request.exam.RemoveQuestionRequest;
import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.repository.ExamRepository;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Page<ExamEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return examRepository.findAll(pageable);
    }

    @Override
    public ExamEntity detail(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public ExamEntity saveOrUpdate(ExamRequest request) {
        // TODO chua xong method nay
        String title = request.getTitle();
        String description = request.getDescription();
        String time = request.getTime();
        Double totalMark = request.getTotalMarks();
        Integer durationMinutes = request.getDurationMinutes();
        Boolean isPublished = request.getIsPublished();
        Long gradeId = request.getGradeId();
        Long subjectId = request.getSubjectId();

        ExamEntity exam;
//        return examRepository.save(exam);
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public void assignQuestions(AssignQuestionRequest request) {
        ExamEntity exam = examRepository
                .findById(request.examId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay bai kiem tra"));

        Set<QuestionEntity> questions = questionRepository.findByIds(request.getQuestionIds());
        if(exam.getQuestions() == null){
            exam.setQuestions(new HashSet<>());
        }
        exam.getQuestions().addAll(questions);
        examRepository.save(exam);
    }

    @Override
    public void removeQuestions(RemoveQuestionRequest request) {
        ExamEntity exam = examRepository
                .findById(request.examId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay bai kiem tra"));

        Set<QuestionEntity> questions = questionRepository.findByIds(request.getQuestionIds());
        exam.getQuestions().removeAll(questions);
        examRepository.save(exam);
    }
}
