package com.mhgjoker.education.system.controller;

import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final UserRepository userRepository;

    @GetMapping("/detail")
    public ResponseEntity<?> demoDetail(@RequestParam("id") Long id){
        UserEntity rs = userRepository.findById(id).orElse(null);
        return ResponseEntity.ok(rs);
    }
}
