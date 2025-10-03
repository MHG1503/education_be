package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.UserRepository;
import com.mhgjoker.education.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<UserEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity detail(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity userEntity) {
        return userRepository.save(userEntity);
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
