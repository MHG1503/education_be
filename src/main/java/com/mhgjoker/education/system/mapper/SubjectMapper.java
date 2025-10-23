package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.subject.SubjectRequest;
import com.mhgjoker.education.system.dto.response.subject.SubjectResponse;
import com.mhgjoker.education.system.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectEntity requestToEntity(SubjectRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "subjectName", target = "subjectName")
    @Mapping(source = "imageUrl", target = "imageUrl")
    SubjectResponse entityToResponse(SubjectEntity subject);
}
