package com.mhgjoker.education.system.dto.request.question;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SearchQuestionRequest {
    String keyword;
    String type;
    Long subjectId;
    String level;
    Integer mark;
    Integer pageNum;
    Integer pageSize;
}
