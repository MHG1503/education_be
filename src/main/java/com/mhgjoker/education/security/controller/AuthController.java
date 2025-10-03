package com.mhgjoker.education.security.controller;

import com.mhgjoker.education.security.dto.request.LoginRequest;
import com.mhgjoker.education.security.dto.request.RegisterRequest;
import com.mhgjoker.education.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        var rs = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(rs);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        var rs = authService.login(request);
        return ResponseEntity.ok(rs);
    }
}
