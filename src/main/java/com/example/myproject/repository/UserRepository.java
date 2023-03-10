package com.example.myproject.repository;

import com.example.myproject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    @Query(value = "SELECT * FROM users u where u.id = ?1", nativeQuery = true)
    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAll();




}
