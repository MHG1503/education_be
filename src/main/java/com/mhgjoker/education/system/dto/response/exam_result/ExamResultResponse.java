package com.mhgjoker.education.system.dto.response.exam_result;

import com.mhgjoker.education.system.dto.response.user_answer.UserAnswerResponse;
import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.entity.UserEntity;
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

    private UserEntity user;

    private ExamEntity exam;

    private List<UserAnswerResponse> userAnswers;

    private Double score;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;
}
