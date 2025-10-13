package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.exam.ExamRequest;
import com.mhgjoker.education.system.entity.ExamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper{

    ExamEntity requestToEntity(ExamRequest request);
}
