package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.response.user.UserResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.UserEntity;

public interface UserService{

    PaginatedResponse<UserResponse> list(Long id, String fullname, String username, String email, Integer pageNum, Integer pageSize);

    UserResponse detail(Long id);

    UserResponse saveOrUpdate(UserEntity userEntity);

    boolean deleteById(Long id);
}
