package com.mhgjoker.education.system.dto.request.question;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private Long id;

    private String content;

    private Long subjectId;

    private Long gradeId;

    private String level;

    private Integer mark;

    private String imageUrl;

    private Set<OptionRequest> options;
}
