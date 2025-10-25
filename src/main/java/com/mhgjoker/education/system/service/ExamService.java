package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.exam.AssignQuestionRequest;
import com.mhgjoker.education.system.dto.request.exam.ExamRequest;
import com.mhgjoker.education.system.dto.request.exam.RemoveQuestionRequest;
import com.mhgjoker.education.system.dto.response.exam.ExamLazyResponse;
import com.mhgjoker.education.system.dto.response.exam.ExamResponse;
import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import org.springframework.data.domain.Page;

public interface ExamService{

    PaginatedResponse<ExamLazyResponse> list(Integer pageNum, Integer pageSize);

    ExamResponse detail(Long id);

    ExamResponse saveOrUpdate(ExamRequest request);

    boolean deleteById(Long id);

    void assignQuestions(AssignQuestionRequest questions);

    void removeQuestions(RemoveQuestionRequest request);
}
