package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.entity.OptionEntity;
import org.springframework.data.domain.Page;

public interface OptionService{

    Page<OptionEntity> list(Integer pageNum, Integer pageSize);

    OptionEntity detail(Long id);

    OptionEntity saveOrUpdate(OptionEntity OptionEntity);

    boolean deleteById(Long id);
}
