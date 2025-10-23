package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.response.exam.ExamResponse;
import com.mhgjoker.education.system.entity.ExamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class, GradeMapper.class, SubjectMapper.class})
public interface ExamMapper{

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "totalMarks", target = "totalMarks")
    @Mapping(source = "durationMinutes", target = "durationMinutes")
    @Mapping(source = "isPublished", target = "isPublished")
    @Mapping(source = "grade", target = "grade")
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "questions", target = "questions")
    ExamResponse entityToResponse(ExamEntity exam);
}
