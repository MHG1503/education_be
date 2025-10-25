package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.response.education_level.EducationLevelResponse;
import com.mhgjoker.education.system.entity.EducationLevelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GradeMapper.class})
public interface EducationLevelMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "grades", target = "grades")
    EducationLevelResponse entityToResponse(EducationLevelEntity entity);
}
