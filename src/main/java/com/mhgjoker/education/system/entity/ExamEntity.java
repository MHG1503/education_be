package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "exam")
public class ExamEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private GradeEntity grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<QuestionEntity> questions;
}
