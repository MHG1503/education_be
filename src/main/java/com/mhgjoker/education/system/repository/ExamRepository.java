package com.mhgjoker.education.system.repository;

import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import com.mhgjoker.education.system.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

    Optional<ExamEntity> findById(Long id, EntityGraph entityGraph);
}
