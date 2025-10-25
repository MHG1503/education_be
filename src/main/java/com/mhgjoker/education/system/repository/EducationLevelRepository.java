package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.EducationLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevelEntity, Long> {
}
