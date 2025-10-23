package com.mhgjoker.education.system.dto.request.exam;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ExamRequest {

     Long id;

     String title;

     String description;

     String time;

     Double totalMarks;

     Integer durationMinutes;

     Boolean isPublished;

     Long gradeId;

     Long subjectId;

     Set<Long> questionIds;
}
