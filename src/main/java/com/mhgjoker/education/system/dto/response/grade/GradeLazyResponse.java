package com.mhgjoker.education.system.dto.response.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeLazyResponse {

    private Long id;

    private String gradeName;

    private String educationLevelName;
}
