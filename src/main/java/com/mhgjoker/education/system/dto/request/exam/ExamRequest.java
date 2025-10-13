package com.mhgjoker.education.system.dto.request.exam;

import com.mhgjoker.education.system.entity.GradeEntity;
import com.mhgjoker.education.system.entity.QuestionEntity;
import com.mhgjoker.education.system.entity.SubjectEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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

     LocalDateTime time;

     Double totalMarks;

     Integer durationMinutes;

     Boolean isPublished;

     GradeEntity grade;

     SubjectEntity subject;

     Set<QuestionEntity> questions;
}
