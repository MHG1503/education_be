package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public Page<SubjectEntity> list(Integer pageNum, Integer pageSize) {
        var pageable = PageRequest.of(pageNum,pageSize);
        return subjectRepository.findAll(pageable);
    }

    @Override
    public SubjectEntity detail(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public SubjectEntity saveOrUpdate(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if(subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public SubjectEntity findBySubjectName(String subjectName) {
        return subjectRepository
                .findBySubjectName(subjectName)
                .orElse(null);
    }
}
