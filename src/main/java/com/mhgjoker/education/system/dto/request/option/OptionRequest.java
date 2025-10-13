package com.mhgjoker.education.system.dto.request.option;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {

    private Long id;

    private Long questionId;

    private String content;

    private Boolean isCorrect;
}
