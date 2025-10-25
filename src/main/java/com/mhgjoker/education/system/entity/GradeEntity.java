package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "grade")
public class GradeEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade_name")
    private String gradeName;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "education_level_id")
    private EducationLevelEntity educationLevel;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grades_subjects",
            joinColumns = @JoinColumn(name = "grade_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<SubjectEntity> subjects = new HashSet<>();

    @PreRemove
    private void removeAssociation(){
        subjects.clear();
    }
}
