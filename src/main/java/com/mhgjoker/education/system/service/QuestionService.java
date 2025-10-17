package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.request.question.SearchQuestionRequest;
import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {

    Page<QuestionEntity> list(SearchQuestionRequest request);

    QuestionEntity detail(Long id);

    QuestionEntity saveOrUpdate(QuestionRequest request);

    boolean deleteById(Long id);

    List<QuestionEntity> saveMany(List<QuestionEntity> entities);

    List<QuestionEntity> updateMany(List<QuestionEntity> entities);


}
