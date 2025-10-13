package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface QuestionService {

    Page<QuestionEntity> list(Integer pageNum, Integer pageSize);

    QuestionEntity detail(Long id);

    QuestionEntity saveOrUpdate(QuestionEntity QuestionEntity);

    boolean deleteById(Long id);

    void importFile(MultipartFile file);

}
