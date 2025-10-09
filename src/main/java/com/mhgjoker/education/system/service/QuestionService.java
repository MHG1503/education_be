package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;

public interface QuestionService {

    Page<QuestionEntity> list(Integer pageNum, Integer pageSize);

    QuestionEntity detail(Long id);

    QuestionEntity saveOrUpdate(QuestionEntity QuestionEntity);

    boolean deleteById(Long id);
}
