package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.UserAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswerEntity, Long> {
}
