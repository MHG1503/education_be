package com.mhgjoker.education.system.dto.request.grade;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequest {

    private Long id;

    private String gradeName;

    private Long educationLevelId;

}
