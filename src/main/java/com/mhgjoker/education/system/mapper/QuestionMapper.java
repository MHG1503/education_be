package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.question.QuestionRequest;
import com.mhgjoker.education.system.dto.response.question.QuestionResponse;
import com.mhgjoker.education.system.entity.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OptionMapper.class})
public interface QuestionMapper {

    QuestionEntity requestToEntity(QuestionRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "subject.subjectName", target = "subjectName")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "mark", target = "mark")
    @Mapping(source = "options", target = "options")
    QuestionResponse entityToResponse(QuestionEntity entity);
}
