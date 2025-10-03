package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService{

    Page<UserEntity> list(Integer pageNum, Integer pageSize);

    UserEntity detail(Long id);

    UserEntity saveOrUpdate(UserEntity userEntity);

    boolean deleteById(Long id);
}
