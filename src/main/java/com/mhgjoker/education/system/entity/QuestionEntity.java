package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "question")
public class QuestionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @Column(name = "mark")
    private Integer mark;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<OptionEntity> options;
}
