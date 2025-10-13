package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {

    Page<QuestionEntity> list(Integer pageNum, Integer pageSize);

    QuestionEntity detail(Long id);

    QuestionEntity saveOrUpdate(QuestionEntity QuestionEntity);

    boolean deleteById(Long id);

    List<QuestionEntity> saveMany(List<QuestionEntity> entities);

    List<QuestionEntity> updateMany(List<QuestionEntity> entities);
}
