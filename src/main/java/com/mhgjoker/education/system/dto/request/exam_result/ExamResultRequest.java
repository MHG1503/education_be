package com.mhgjoker.education.system.dto.request.exam_result;

import com.mhgjoker.education.system.dto.request.user_answer.UserAnswerRequest;
import com.mhgjoker.education.system.entity.ExamEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ExamResultRequest {

    Long examId;

    List<UserAnswerRequest> answerRequests;

    LocalDateTime startedAt;

    LocalDateTime finishedAt;
}
