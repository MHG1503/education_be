package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "exam_result", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","exam_id"})
})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "exam_result_with_user_answers",
                attributeNodes = {
                    @NamedAttributeNode("user"),
                    @NamedAttributeNode("userAnswers"),
                    @NamedAttributeNode("exam")
        })
})
public class ExamResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "examResult", cascade = CascadeType.ALL)
    private List<UserAnswerEntity> userAnswers;

    @Column(name = "score")
    private Double score;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;
}
