package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import com.mhgjoker.education.system.dto.response.option.OptionResponse;
import com.mhgjoker.education.system.entity.OptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    OptionEntity requestToEntity(OptionRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "isCorrect", target = "isCorrect")
    OptionResponse entityToResponse(OptionEntity entity);
}
