package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.response.education_level.EducationLevelResponse;
import com.mhgjoker.education.system.entity.EducationLevelEntity;
import java.util.List;

public interface EducationLevelService {

    List<EducationLevelResponse> list();

    EducationLevelResponse detail(Long id);

    EducationLevelResponse saveOrUpdate(EducationLevelEntity examEntity);

    boolean deleteById(Long id);
}
