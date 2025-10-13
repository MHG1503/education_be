package com.mhgjoker.education.system.service.impl;

import com.mhgjoker.education.system.entity.GradeEntity;
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

    @Override
    public Page<GradeEntity> list(Integer pageNum, Integer pageSize) {
        return gradeRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public GradeEntity detail(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    @Override
    public GradeEntity saveOrUpdate(GradeEntity gradeEntity) {
        return gradeRepository.save(gradeEntity);
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
    }
}
