package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@Table(name = "question", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"content", "type", "subject_id"}
        )
})
public class QuestionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 20000)
    private String content;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

    @Column(name = "level")
    private String level;

    @Column(name = "mark")
    private Integer mark;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "question")
    private Set<OptionEntity> options;

    public QuestionEntity() {
        this.options = new HashSet<>();
    }
}
