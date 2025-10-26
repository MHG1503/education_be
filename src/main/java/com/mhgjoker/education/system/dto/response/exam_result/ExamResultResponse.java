package com.mhgjoker.education.system.dto.response.exam_result;

import com.mhgjoker.education.system.dto.response.exam.ExamResponse;
import com.mhgjoker.education.system.dto.response.user.UserResponse;
import com.mhgjoker.education.system.dto.response.user_answer.UserAnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultResponse {

    private Long id;

    private UserResponse user;

    private ExamResponse exam;

    private List<UserAnswerResponse> userAnswers;

    private Double score;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;
}
