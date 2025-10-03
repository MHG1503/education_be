package com.mhgjoker.education.security.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    String username;
    String email;
    String role;
}
