package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import org.springframework.data.domain.Page;

public interface ExamResultService{

    Page<ExamResultEntity> list(Integer pageNum, Integer pageSize);

    ExamResultEntity detail(Long id);

    ExamResultEntity saveOrUpdate(ExamResultEntity examEntity);

    boolean deleteById(Long id);

    void submitExam(ExamResultRequest request);
}
