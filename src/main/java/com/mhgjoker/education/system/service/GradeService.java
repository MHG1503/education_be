package com.mhgjoker.education.system.service;

import com.mhgjoker.education.system.dto.request.grade.GradeRequest;
import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.entity.PaginatedResponse;

public interface GradeService {

    PaginatedResponse<GradeResponse> list(Integer pageNum, Integer pageSize);

    GradeResponse detail(Long id);

    GradeResponse saveOrUpdate(GradeRequest request);

    boolean deleteById(Long id);

    void assignSubject(Long gradeId, Long subjectId);

    void removeSubject(Long gradeId, Long subjectId);
}
