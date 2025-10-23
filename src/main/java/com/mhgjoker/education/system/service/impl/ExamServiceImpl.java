package com.mhgjoker.education.system.service.impl;

import com.cosium.spring.data.jpa.entity.graph.domain2.NamedEntityGraph;
import com.mhgjoker.education.system.dto.request.exam.AssignQuestionRequest;
import com.mhgjoker.education.system.dto.request.exam.ExamRequest;
import com.mhgjoker.education.system.dto.request.exam.RemoveQuestionRequest;
import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.entity.GradeEntity;
import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.repository.ExamRepository;
import com.mhgjoker.education.system.repository.GradeRepository;
import com.mhgjoker.education.system.repository.QuestionRepository;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public Page<ExamEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return examRepository.findAll(pageable);
    }

    @Override
    public ExamEntity detail(Long id) {
        return examRepository
                .findById(id, NamedEntityGraph.fetching("exam_with_grade_subject_questions"))
                .orElse(null);
    }

    @Override
    public ExamEntity saveOrUpdate(ExamRequest request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // TODO chua xong method nay
        Long examId = request.getId();
        String title = request.getTitle();
        String description = request.getDescription();
        String time = request.getTime();
        Double totalMark = request.getTotalMarks();
        Integer durationMinutes = request.getDurationMinutes();
        Boolean isPublished = request.getIsPublished();
        Long gradeId = request.getGradeId();
        Long subjectId = request.getSubjectId();
        Set<Long> questionIds = request.getQuestionIds();
        GradeEntity grade = gradeRepository
                .findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lớp"));

        SubjectEntity subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));


        List<QuestionEntity> questions = questionRepository
                .findByIds(questionIds.stream().toList());

        ExamEntity exam;
        if(request.getId() != null){
            exam = examRepository
                    .findById(examId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy bài thi này"));
        }else{
            exam = new ExamEntity();
        }

        LocalDateTime examTime = LocalDateTime.parse(time, formatter);

        exam.setTitle(title);
        exam.setDescription(description);
        exam.setTime(examTime);
        exam.setTotalMarks(totalMark);
        exam.setDurationMinutes(durationMinutes);
        exam.setIsPublished(isPublished);
        exam.setGrade(grade);
        exam.setSubject(subject);
        exam.setQuestions(new HashSet<>(questions));
        return examRepository.save(exam);
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

        List<QuestionEntity> questions = questionRepository.findByIds(request.getQuestionIds());
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

        List<QuestionEntity> questions = questionRepository.findByIds(request.getQuestionIds());
        questions.forEach(exam.getQuestions()::remove);
        examRepository.save(exam);
    }
}
