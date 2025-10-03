package com.mhgjoker.education.security.service;

import com.mhgjoker.education.security.common.utils.JwtTokenUtils;
import com.mhgjoker.education.security.dto.request.LoginRequest;
import com.mhgjoker.education.security.dto.request.RegisterRequest;
import com.mhgjoker.education.security.dto.response.LoginResponse;
import com.mhgjoker.education.security.dto.response.RegisterResponse;
import com.mhgjoker.education.security.model.JwtUser;
import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.RoleRepository;
import com.mhgjoker.education.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest request){
        RoleEntity role = roleRepository
                .findByName("USER")
                .orElseThrow(() -> new RuntimeException("khong tim thay thong tin role"));

        Optional<UserEntity> userOptional = userRepository.findByEmail(request.email());
        UserEntity user = null;

        if(userOptional.isPresent()){
            user = userOptional.get();
            if(user.getUsername().equals(request.username())){
                throw new RuntimeException("Da ton tai username");
            }

            throw new RuntimeException("Da ton tai email");
        }else{
            user = UserEntity
                    .builder()
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .username(request.username())
                    .image(null)
                    .role(role)
                    .enabled(false)
                    .build();

            var savedUser = userRepository.save(user);
            var response = new RegisterResponse();
            response.setEmail(savedUser.getEmail());
            response.setRole(savedUser.getRole().getName());
            response.setUsername(savedUser.getUsername());
            return response;
        }
    }

    public LoginResponse login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),loginRequest.password());
        Authentication responseAuthentication = authenticationManager.authenticate(authenticationToken);
        if(responseAuthentication != null && responseAuthentication.isAuthenticated()){
            String token = JwtTokenUtils.createToken((UserDetails) responseAuthentication.getPrincipal());

            return new LoginResponse(
                    token,((JwtUser) responseAuthentication.getPrincipal()).getLoginUsername() ,String.join(",",
                    responseAuthentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .map(r -> r.substring(5))
                            .toList()
            ));
        }
        throw new RuntimeException("Login failed");
    }
}
