package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.ExamResultEntity;
import com.mhgjoker.education.system.repository.ExamResultRepository;
import com.mhgjoker.education.system.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepository examResultRepository;

    @Override
    public Page<ExamResultEntity> list(Integer pageNum, Integer pageSize) {
        Pageable pageable =  PageRequest.of(pageNum, pageSize);
        return examResultRepository.findAll(pageable);
    }

    @Override
    public ExamResultEntity detail(Long id) {
        return examResultRepository.findById(id).orElse(null);
    }

    @Override
    public ExamResultEntity saveOrUpdate(ExamResultEntity examEntity) {
        return examResultRepository.save(examEntity);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if(examResultRepository.existsById(id)){
            examResultRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
