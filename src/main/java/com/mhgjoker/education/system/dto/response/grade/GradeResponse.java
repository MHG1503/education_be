package com.mhgjoker.education.system.dto.response.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {

    private Long id;

    private String subjectName;

    private String imageUrl;
}
