package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.ExamEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ExamService{

    Page<ExamEntity> list(Integer pageNum, Integer pageSize);

    ExamEntity detail(Long id);

    ExamEntity saveOrUpdate(ExamEntity examEntity);

    boolean deleteById(Long id);
}
