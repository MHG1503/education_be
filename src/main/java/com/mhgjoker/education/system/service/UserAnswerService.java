package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.UserAnswerEntity;
import org.springframework.data.domain.Page;

public interface UserAnswerService {

    Page<UserAnswerEntity> list(Integer pageNum, Integer pageSize);

    UserAnswerEntity detail(Long id);

    UserAnswerEntity saveOrUpdate(UserAnswerEntity userAnswerEntity);
}
