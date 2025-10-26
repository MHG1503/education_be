package com.mhgjoker.education.system.dto.response.user_answer;

import com.mhgjoker.education.system.dto.response.option.OptionResponse;
import com.mhgjoker.education.system.dto.response.question.QuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerResponse {

    private Long id;

    private QuestionResponse question;

    private OptionResponse selectedOption;

    private Boolean isCorrect;
}
