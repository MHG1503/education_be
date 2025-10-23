package com.mhgjoker.education.system.dto.response.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {

    private Long id;

    private String subjectName;

    private String imageUrl;
}
