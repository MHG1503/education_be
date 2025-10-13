package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.grade.GradeRequest;
import com.mhgjoker.education.system.entity.GradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "subjectName", target = "subjectName")
    @Mapping(source = "imageUrl",target = "imageUrl")
    GradeEntity requestToEntity(GradeRequest request);
}
