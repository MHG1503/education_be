package com.mhgjoker.education.system.mapper;

import com.mhgjoker.education.system.dto.request.option.OptionRequest;
import com.mhgjoker.education.system.entity.OptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    OptionEntity requestToEntity(OptionRequest request);
}
