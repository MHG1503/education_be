package com.mhgjoker.education.system.dto.response.exam_result;

import com.mhgjoker.education.system.dto.response.exam.ExamLazyResponse;
import com.mhgjoker.education.system.dto.response.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultLazyResponse {

    private Long id;

    private UserResponse user;

    private ExamLazyResponse exam;

    private Double score;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;
}
