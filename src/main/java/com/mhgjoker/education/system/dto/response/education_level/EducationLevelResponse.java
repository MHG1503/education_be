package com.mhgjoker.education.system.dto.response.education_level;

import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.entity.GradeEntity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationLevelResponse {

    private Long id;
    private String code;   // Ví dụ: "THCS", "THPT"
    private String name;   // Ví dụ: "Trung học cơ sở", "Trung học phổ thông"
    private List<GradeResponse> grades;
}
