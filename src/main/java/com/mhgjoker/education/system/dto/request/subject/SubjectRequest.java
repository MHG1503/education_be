package com.mhgjoker.education.system.dto.request.subject;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SubjectRequest {

    Long id;

    String subjectName;

    String imageUrl;
}
