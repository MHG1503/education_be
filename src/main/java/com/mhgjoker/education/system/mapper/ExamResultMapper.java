package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.response.exam_result.ExamResultLazyResponse;
import com.mhgjoker.education.system.dto.response.exam_result.ExamResultResponse;
import com.mhgjoker.education.system.entity.ExamResultEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ExamMapper.class, UserAnswerMapper.class})
public interface ExamResultMapper {

    ExamResultLazyResponse entityToLazyResponse(ExamResultEntity entity);

    ExamResultResponse entityToResponse(ExamResultEntity entity);
}
