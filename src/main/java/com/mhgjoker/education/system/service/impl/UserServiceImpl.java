package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.response.user.UserResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.mapper.UserMapper;
import com.mhgjoker.education.system.repository.UserRepository;
import com.mhgjoker.education.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PaginatedResponse<UserResponse> list(Long id, String fullname, String username, String email, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        if(!StringUtils.hasText(fullname)){
            fullname = null;
        }
        if(!StringUtils.hasText(username)){
            username = null;
        }
        if(!StringUtils.hasText(email)){
            email = null;
        }

        var rs = userRepository.search(id, fullname, username, email, pageable);

        return new PaginatedResponse<>(
                rs.getContent().stream().map(userMapper::entityToResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public UserResponse detail(Long id) {
        return userMapper.entityToResponse(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserResponse saveOrUpdate(UserEntity userEntity) {
        return userMapper.entityToResponse(userRepository.save(userEntity));
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
