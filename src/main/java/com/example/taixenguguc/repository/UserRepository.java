package com.example.taixenguguc.repository;

import com.example.taixenguguc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByPhone(String phone);
    Optional<User> findByPhone(String phone);
}
