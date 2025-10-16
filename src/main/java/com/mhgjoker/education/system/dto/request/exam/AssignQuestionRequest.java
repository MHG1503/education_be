package com.mhgjoker.education.system.dto.request.exam;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AssignQuestionRequest {

    Long examId;

    List<Long> questionIds;
}
