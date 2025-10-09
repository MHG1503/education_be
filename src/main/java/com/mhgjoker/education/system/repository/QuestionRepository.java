package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
}
