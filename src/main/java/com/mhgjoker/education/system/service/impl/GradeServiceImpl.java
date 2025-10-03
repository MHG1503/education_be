package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.GradeEntity;
import com.mhgjoker.education.system.repository.GradeRepository;
import com.mhgjoker.education.system.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Override
    public Page<GradeEntity> list(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public GradeEntity detail(Long id) {
        return null;
    }

    @Override
    public GradeEntity saveOrUpdate(GradeEntity gradeEntity) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
