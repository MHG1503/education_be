package com.mhgjoker.education.system.dto.request.question;

import com.mhgjoker.education.system.entity.OptionEntity;
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

    private String type;

    private Integer mark;

    private Set<OptionEntity> options;
}
