package com.mhgjoker.education.system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "education_level")
public class EducationLevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;   // Ví dụ: "THCS", "THPT"
    private String name;   // Ví dụ: "Trung học cơ sở", "Trung học phổ thông"

    @OneToMany(mappedBy = "educationLevel")
    private List<GradeEntity> grades;

    @PreRemove
    private void removeAssociation(){
        for (var grade : grades){
            grade.setEducationLevel(null);
        }
    }
}
