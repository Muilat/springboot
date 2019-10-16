package com.example.demo.repository;

import com.example.demo.model.DeptUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptUserRepository extends JpaRepository<DeptUser, Integer> {
    List<DeptUser> getAllByDeptContainingAndNameContainingIgnoreCaseAndSalaryGreaterThanEqual(String d, String n, Integer s);

}
