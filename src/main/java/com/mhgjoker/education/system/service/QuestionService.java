package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.request.question.SearchQuestionRequest;
import com.mhgjoker.education.system.dto.response.question.QuestionResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {

    PaginatedResponse<QuestionResponse> list(SearchQuestionRequest request);

    QuestionResponse detail(Long id);

    QuestionResponse saveOrUpdate(QuestionRequest request);

    boolean deleteById(Long id);

    List<QuestionResponse> saveMany(List<QuestionEntity> entities);

    List<QuestionResponse> updateMany(List<QuestionEntity> entities);


}
