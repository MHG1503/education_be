package com.mhgjoker.education.system.dto.response.option;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {

    private Long id;

    private Long questionId;

    private String content;

    private Boolean isCorrect;
}
