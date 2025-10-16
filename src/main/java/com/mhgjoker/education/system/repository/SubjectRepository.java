package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findBySubjectName(@Param("subjectName") String subjectName);
}
