package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.response.user.UserResponse;
import com.mhgjoker.education.system.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.name", target = "role")
    UserResponse entityToResponse(UserEntity entity);
}
