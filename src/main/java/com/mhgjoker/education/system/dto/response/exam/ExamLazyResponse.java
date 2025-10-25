package com.mhgjoker.education.system.dto.response.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamLazyResponse {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime time;

    private Double totalMarks;

    private Integer durationMinutes;

    private Boolean isPublished;

    private String gradeName;

    private String subjectName;
}
