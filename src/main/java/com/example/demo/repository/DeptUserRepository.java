package com.example.demo.repository;

import com.example.demo.model.DeptUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptUserRepository extends JpaRepository<DeptUser, Integer> {
}
