package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.ExamResultEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cosium.spring.data.jpa.entity.graph.domain2.EntityGraph;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResultEntity, Long> {
    // Lấy tất cả lần làm bài của user
    List<ExamResultEntity> findByUserId(@Param("userId") Long userId, Pageable pageable);

    Optional<ExamResultEntity> findById(Long id, EntityGraph entityGraph);


    @Query(value = """
            SELECT * FROM exam_result er INNER JOIN user_answer ua ON er.id = ua.exam_result_id
            """,
    nativeQuery = true)
    Object findByIdWithUserAnswerDetail(@Param("id") Long id);

    Optional<ExamResultEntity> findByUserIdAndExamId(@Param("userId") Long userId,
                                                     @Param("examId") Long examId,
                                                     EntityGraph entityGraph);
}
