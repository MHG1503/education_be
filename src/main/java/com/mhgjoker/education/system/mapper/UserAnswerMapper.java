package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.response.user_answer.UserAnswerResponse;
import com.mhgjoker.education.system.entity.UserAnswerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class, OptionMapper.class})
public interface UserAnswerMapper {

    UserAnswerResponse entityToResponse(UserAnswerEntity entity);
}
