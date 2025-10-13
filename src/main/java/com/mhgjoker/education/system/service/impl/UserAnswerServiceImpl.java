package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.UserAnswerEntity;
import com.mhgjoker.education.system.repository.UserAnswerRepository;
import com.mhgjoker.education.system.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;

    @Override
    public Page<UserAnswerEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return userAnswerRepository.findAll(pageable);
    }

    @Override
    public UserAnswerEntity detail(Long id) {
        return userAnswerRepository.findById(id).orElse(null);
    }

    @Override
    public UserAnswerEntity saveOrUpdate(UserAnswerEntity userAnswerEntity) {
        return userAnswerRepository.save(userAnswerEntity);
    }
}
