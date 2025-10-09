package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.ExamEntity;
import com.mhgjoker.education.system.repository.ExamRepository;
import com.mhgjoker.education.system.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public Page<ExamEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return examRepository.findAll(pageable);
    }

    @Override
    public ExamEntity detail(Long id) {
        return examRepository.findById(id).orElse(null);
    }

    @Override
    public ExamEntity saveOrUpdate(ExamEntity examEntity) {
        return examRepository.save(examEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
