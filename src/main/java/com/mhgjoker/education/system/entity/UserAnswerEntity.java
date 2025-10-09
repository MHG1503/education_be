package com.mhgjoker.education.system.entity;

import com.mhgjoker.education.system.entity.id.UserAnswerId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_answer")
public class UserAnswerEntity extends BaseEntity{

    @EmbeddedId
    private UserAnswerId id;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}
