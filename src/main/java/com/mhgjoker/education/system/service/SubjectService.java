package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.RoleEntity;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface SubjectService {

    Page<SubjectEntity> list(Integer pageNum, Integer pageSize);

    SubjectEntity detail(Long id);

    SubjectEntity saveOrUpdate(SubjectEntity subjectEntity);

    boolean deleteById(Long id);

    SubjectEntity findBySubjectName(String subjectName);
}
