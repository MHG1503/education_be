package com.mhgjoker.education.security.config;

import com.mhgjoker.education.security.model.JwtUser;
import com.mhgjoker.education.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Khong tim thay user"));

        return new JwtUser(user);
    }
}
