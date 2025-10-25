package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.grade.GradeRequest;
import com.mhgjoker.education.system.dto.response.grade.GradeLazyResponse;
import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.entity.GradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "gradeName", target = "gradeName")
    GradeEntity requestToEntity(GradeRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "gradeName", target = "gradeName")
    @Mapping(source = "educationLevel.name", target = "educationLevelName")
    GradeResponse entityToResponse(GradeEntity grade);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "gradeName", target = "gradeName")
    @Mapping(source = "educationLevel.name", target = "educationLevelName")
    GradeLazyResponse entityToLazyResponse(GradeEntity grade);
}
