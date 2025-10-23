package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {

    @Query("SELECT qe FROM QuestionEntity qe WHERE qe.id IN :ids")
    List<QuestionEntity> findByIds(@Param("ids") List<Long> ids);

    @Query(value = """
            SELECT * FROM question
            WHERE (:keyword IS NULL OR content LIKE %:keyword%)
            AND (:type IS NULL OR type = :type)
            AND (:subjectId IS NULL OR subject_id = :subjectId)
            AND (:level IS NULL OR level = :level)
            AND (:mark IS NULL OR mark = :mark)
            """,
            nativeQuery = true)
    Page<QuestionEntity> search(@Param("keyword") String keyword,
                                        @Param("type") String type,
                                        @Param("subjectId") Long subjectId,
                                        @Param("level") String level,
                                        @Param("mark") Integer mark,
                                        Pageable pageable);
}
