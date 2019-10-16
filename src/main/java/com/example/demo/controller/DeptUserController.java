package com.example.demo.controller;

import com.example.demo.config.DataBatchConfig;
import com.example.demo.model.DeptUser;
import com.example.demo.repository.DeptUserRepository;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/deptusers")

public class DeptUserController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    DeptUserRepository deptUserRepository;


    @GetMapping(value = "/load")
    public ResponseEntity loadDeptUserCSV() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("JobExecution: " + jobExecution.getStatus());

        System.out.println("Batch is Running...");
        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/")
    public ResponseEntity getAll(){
        List<DeptUser> users = deptUserRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/query")
    public ResponseEntity getAllWithQuery(String dept, String name, Integer salary){
//        System.out.println("dept = " + dept + ", name = " + name + ", salary = " + salary);
        List<DeptUser> users = deptUserRepository.getAllByDeptContainingAndNameContainingIgnoreCaseAndSalaryGreaterThanEqual(dept, name, salary);
        return ResponseEntity.ok().body(users);
    }
}
