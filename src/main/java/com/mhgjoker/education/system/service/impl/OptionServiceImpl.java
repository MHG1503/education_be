package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.OptionEntity;
import com.mhgjoker.education.system.repository.OptionRepository;
import com.mhgjoker.education.system.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public Page<OptionEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return optionRepository.findAll(pageable);
    }

    @Override
    public OptionEntity detail(Long id) {
        return optionRepository.findById(id).orElse(null);
    }

    @Override
    public OptionEntity saveOrUpdate(OptionEntity OptionEntity) {
        return optionRepository.save(OptionEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (optionRepository.existsById(id)) {
            optionRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
