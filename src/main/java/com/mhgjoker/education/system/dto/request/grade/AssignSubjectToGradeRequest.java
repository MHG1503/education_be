package com.mhgjoker.education.system.dto.request.grade;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
public class AssignSubjectToGradeRequest {
    Long gradeId;
    Long subjectId;
}
