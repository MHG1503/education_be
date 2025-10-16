package com.mhgjoker.education.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_answer")
public class UserAnswerEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_result_id", nullable = false)
    @JsonIgnore
    private ExamResultEntity examResult;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "selected_option_id")
    private OptionEntity selectedOption; // nullable

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
