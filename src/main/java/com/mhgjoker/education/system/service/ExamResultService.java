package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.exam_result.ExamResultRequest;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultLazyResponse;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultResponse;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.UserEntity;

public interface ExamResultService{

    PaginatedResponse<ExamResultLazyResponse> list(Integer pageNum, Integer pageSize);

    PaginatedResponse<ExamResultLazyResponse> listByUserId(Long userId, Integer pageNum, Integer pageSize);

    ExamResultResponse detail(Long id);

    ExamResultResponse saveOrUpdate(ExamResultEntity examEntity);

    boolean deleteById(Long id);

    void submitExam(UserEntity user, ExamResultRequest request);

    ExamResultResponse detailByUserIdAndExamId(Long userId, Long examId);
}
