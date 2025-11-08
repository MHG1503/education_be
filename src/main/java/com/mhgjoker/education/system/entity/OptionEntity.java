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
@Table(name = "options")
public class OptionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private QuestionEntity question;

    @Column(name = "content")
    private String content;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "image_url")
    private String imageUrl;
}
