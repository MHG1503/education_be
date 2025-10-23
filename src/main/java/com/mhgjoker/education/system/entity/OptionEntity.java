package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "options", uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "content"}))
public class OptionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(name = "content")
    private String content;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
