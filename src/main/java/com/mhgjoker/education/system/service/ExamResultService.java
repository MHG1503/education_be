package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExamResultService{

    Page<ExamResultEntity> list(Integer pageNum, Integer pageSize);

    List<ExamResultEntity> listByUserId(Long userId, Integer pageNum, Integer pageSize);

    ExamResultEntity detail(Long id);

    ExamResultEntity saveOrUpdate(ExamResultEntity examEntity);

    boolean deleteById(Long id);

    void submitExam(UserEntity user, ExamResultRequest request);

    ExamResultEntity detailByUserIdAndExamId(Long userId, Long examId);
}
