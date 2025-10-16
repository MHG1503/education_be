package com.mhgjoker.education.security.common.utils;

import com.mhgjoker.education.security.model.JwtUser;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtils {

    private final UserRepository userRepository;

    public String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null){
            return ((JwtUser) authentication.getPrincipal()).getUsername();
        }
        return null;
    }

    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof JwtUser jwtUser){
            return userRepository
                    .findByEmail(jwtUser.getUsername())
                    .orElseThrow(()-> new RuntimeException("Not found"));
        }
        return null;
    }

    public Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() != null && authentication.getPrincipal() instanceof JwtUser jwtUser){
            return jwtUser.getId();
        }
        return null;
    }
}
