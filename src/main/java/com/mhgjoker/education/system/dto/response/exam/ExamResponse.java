package com.mhgjoker.education.system.dto.response.exam;

import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.dto.response.question.QuestionResponse;
import com.mhgjoker.education.system.dto.response.subject.SubjectResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime time;

    private Double totalMarks;

    private Integer durationMinutes;

    private Boolean isPublished;

    private GradeResponse grade;

    private SubjectResponse subject;

    private Set<QuestionResponse> questions;

}
