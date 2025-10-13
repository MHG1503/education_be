package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.subject.SubjectRequest;
import com.mhgjoker.education.system.entity.SubjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectEntity requestToEntity(SubjectRequest request);
}
