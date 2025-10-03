package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.RoleRepository;
import com.mhgjoker.education.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Page<RoleEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return roleRepository.findAll(pageable);
    }

    @Override
    public RoleEntity detail(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public RoleEntity saveOrUpdate(UserEntity userEntity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
