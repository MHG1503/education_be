package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "exam")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "exam_with_grade_subject_questions",attributeNodes = {
                @NamedAttributeNode("grade"),
                @NamedAttributeNode("subject"),
                @NamedAttributeNode("questions")
        })
})
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

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(name = "is_published")
    private Boolean isPublished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private GradeEntity grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "exam")
    private List<ExamResultEntity> examResults;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(
            name = "exams_question",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<QuestionEntity> questions;

    @PreRemove
    private void removeBeforeDelete(){

        examResults.forEach( er -> er.setExam(null));

        if(questions != null) {
            questions.clear();
        }
    }
}
