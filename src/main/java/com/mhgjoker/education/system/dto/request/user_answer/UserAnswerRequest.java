package com.mhgjoker.education.system.dto.request.user_answer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserAnswerRequest {

    Long questionId;

    Long selectedOptionId; // nullable
}
