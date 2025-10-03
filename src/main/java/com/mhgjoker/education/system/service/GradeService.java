package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.GradeEntity;
import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface GradeService {

    Page<GradeEntity> list(Integer pageNum, Integer pageSize);

    GradeEntity detail(Long id);

    GradeEntity saveOrUpdate(GradeEntity gradeEntity);

    boolean deleteById(Long id);
}
