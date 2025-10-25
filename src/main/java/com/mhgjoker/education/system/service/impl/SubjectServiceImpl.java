package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.subject.SubjectRequest;
import com.mhgjoker.education.system.dto.response.subject.SubjectResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.SubjectEntity;
import com.mhgjoker.education.system.mapper.SubjectMapper;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public PaginatedResponse<SubjectResponse> list(Integer pageNum, Integer pageSize) {
        var pageable = PageRequest.of(pageNum,pageSize);
        var rs = subjectRepository.findAll(pageable);

        var content = rs.getContent().stream().map(subjectMapper::entityToResponse).toList();
        return new PaginatedResponse<>(
                rs.getContent().stream().map(subjectMapper::entityToResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public SubjectResponse detail(Long id) {
        return subjectMapper.entityToResponse(subjectRepository.findById(id).orElse(null));
    }

    @Override
    public SubjectResponse saveOrUpdate(SubjectRequest request) {
        var subjectEntity = subjectMapper.requestToEntity(request);
        return subjectMapper.entityToResponse(subjectRepository.save(subjectEntity));
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
