package com.example.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class DeptUser {
    @Id
    private Integer id;
    private String name;
    private String dept;
    private Integer salary;
    private Date time;

    public DeptUser() {
    }
}
