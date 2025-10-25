package com.mhgjoker.education.system.dto.response.user;

import com.mhgjoker.education.system.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String fullname;

    private String username;

    private String email;

    private String image;

    private String role;
}
