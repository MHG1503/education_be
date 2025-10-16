package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.exam.AssignQuestionRequest;
import com.mhgjoker.education.system.dto.request.exam.RemoveQuestionRequest;
import com.mhgjoker.education.system.entity.ExamEntity;
import org.springframework.data.domain.Page;

public interface ExamService{

    Page<ExamEntity> list(Integer pageNum, Integer pageSize);

    ExamEntity detail(Long id);

    ExamEntity saveOrUpdate(ExamEntity examEntity);

    boolean deleteById(Long id);

    void assignQuestions(AssignQuestionRequest questions);

    void removeQuestions(RemoveQuestionRequest request);
}
