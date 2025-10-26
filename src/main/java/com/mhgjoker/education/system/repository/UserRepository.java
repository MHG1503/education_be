package com.mhgjoker.education.system.repository;

import com.mhgjoker.education.system.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    @Query(value = "SELECT u FROM UserEntity u WHERE (:id IS NULL OR u.id = :id) AND (:fullname IS NULL OR u.fullname LIKE %:fullname%) AND (:username IS NULL OR u.username LIKE %:username%) AND (:email IS NULL OR u.email LIKE %:email%)")
    Page<UserEntity> search(@Param("id") Long id,
                            @Param("fullname") String fullname,
                            @Param("username") String username,
                            @Param("email") String email,
                            Pageable pageable);
}
