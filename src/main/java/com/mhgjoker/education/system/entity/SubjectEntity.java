package com.mhgjoker.education.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "subject")
public class SubjectEntity extends BaseEntity{

    @Id
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "image_url")
    private String imageUrl;
}
