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
                columnNames = {"content", "type"}
        )
})
public class QuestionEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @Column(name = "mark")
    private Integer mark;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "question")
    private Set<OptionEntity> options;

    public QuestionEntity() {
        this.options = new HashSet<>();
    }
}
