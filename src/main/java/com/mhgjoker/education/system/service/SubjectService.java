package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.subject.SubjectRequest;
import com.mhgjoker.education.system.dto.response.subject.SubjectResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.entity.SubjectEntity;

public interface SubjectService {

    PaginatedResponse<SubjectResponse> list(Integer pageNum, Integer pageSize);

    SubjectResponse detail(Long id);

    SubjectResponse saveOrUpdate(SubjectRequest request);

    boolean deleteById(Long id);

    SubjectEntity findBySubjectName(String subjectName);
}
