package com.mhgjoker.education.system.entity.id;

import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.entity.OptionEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @ManyToOne
    @JoinColumn(name = "selected_option_id")
    private OptionEntity selectedOption;
}
