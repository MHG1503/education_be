package com.mhgjoker.education.system.dto.response.question;

import com.mhgjoker.education.system.dto.response.option.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Long id;

    private String content;

    private Long subjectId;

    private String subjectName;

    private String level;

    private Integer mark;

    private Set<OptionResponse> options;
}
