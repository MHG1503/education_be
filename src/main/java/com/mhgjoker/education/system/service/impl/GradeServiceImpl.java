package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.dto.request.grade.GradeRequest;
import com.mhgjoker.education.system.dto.response.grade.GradeResponse;
import com.mhgjoker.education.system.entity.EducationLevelEntity;
import com.mhgjoker.education.system.entity.GradeEntity;
import com.mhgjoker.education.system.entity.PaginatedResponse;
import com.mhgjoker.education.system.mapper.GradeMapper;
import com.mhgjoker.education.system.repository.EducationLevelRepository;
import com.mhgjoker.education.system.repository.GradeRepository;
import com.mhgjoker.education.system.repository.SubjectRepository;
import com.mhgjoker.education.system.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final EducationLevelRepository educationLevelRepository;
    private final GradeMapper gradeMapper;

    @Override
    public PaginatedResponse<GradeResponse> list(Integer pageNum, Integer pageSize) {
        var rs = gradeRepository.findAll(PageRequest.of(pageNum, pageSize));

        return new PaginatedResponse<>(
                rs.getContent().stream().map(gradeMapper::entityToResponse).toList(),
                rs.getTotalPages(),
                rs.getNumber(),
                rs.getTotalElements()
        );
    }

    @Override
    public GradeResponse detail(Long id) {
        return gradeMapper.entityToResponse(gradeRepository
                .findById(id)
                .orElse(null));
    }

    @Override
    public GradeResponse saveOrUpdate(GradeRequest request) {
        GradeEntity gradeEntity;

        if (request.getId() != null) {
            // --- Update ---
            gradeEntity = gradeRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Grade not found"));

            gradeEntity.setGradeName(request.getGradeName());
        } else {
            // --- Create ---
            gradeEntity = gradeMapper.requestToEntity(request);
        }

        if (request.getEducationLevelId() != null) {
            EducationLevelEntity educationLevel = educationLevelRepository
                    .findById(request.getEducationLevelId())
                    .orElseThrow(() -> new RuntimeException("Education level not found"));
            gradeEntity.setEducationLevel(educationLevel);
        }

        var saved = gradeRepository.save(gradeEntity);
        return gradeMapper.entityToResponse(saved);
    }


    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        if(gradeRepository.existsById(id)){
            gradeRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public void assignSubject(Long gradeId, Long subjectId) {
        var grade = gradeRepository.findById(gradeId).orElse(null);
        var subject = subjectRepository.findById(subjectId).orElse(null);

        if(grade == null || subject == null){
            throw new RuntimeException("Thieu thong tin dau vao cua grade hoac subject");
        }

        if(grade.getSubjects() == null){
            grade.setSubjects(new HashSet<>());
        }
        grade.getSubjects().add(subject);
        gradeRepository.save(grade);
    }

    @Override
    public void removeSubject(Long gradeId, Long subjectId) {
        var grade = gradeRepository.findById(gradeId).orElse(null);
        var subject = subjectRepository.findById(subjectId).orElse(null);

        if(grade == null || subject == null){
            throw new RuntimeException("Khong tim thay du lieu");
        }

        grade.getSubjects().removeIf(sub -> sub.getId().equals(subject.getId()));
        gradeRepository.save(grade);
    }
}
