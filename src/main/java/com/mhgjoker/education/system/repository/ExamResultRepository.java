package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.ExamResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {
}
