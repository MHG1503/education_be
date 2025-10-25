package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.response.education_level.EducationLevelResponse;
import com.mhgjoker.education.system.entity.EducationLevelEntity;
import com.mhgjoker.education.system.mapper.EducationLevelMapper;
import com.mhgjoker.education.system.repository.EducationLevelRepository;
import com.mhgjoker.education.system.service.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationLevelServiceImpl implements EducationLevelService {

    private final EducationLevelRepository repository;
    private final EducationLevelMapper educationLevelMapper;

    @Override
    public List<EducationLevelResponse> list() {
        return repository.findAll().stream().map(educationLevelMapper::entityToResponse).toList();
    }

    @Override
    public EducationLevelResponse detail(Long id) {
        return educationLevelMapper.entityToResponse(repository.findById(id).orElse(null));
    }

    @Override
    public EducationLevelResponse saveOrUpdate(EducationLevelEntity examEntity) {
        return educationLevelMapper.entityToResponse(repository.save(examEntity));
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (repository.existsById(id)) {
            repository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
