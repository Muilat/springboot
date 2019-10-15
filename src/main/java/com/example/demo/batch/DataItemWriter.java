package com.example.demo.batch;

import com.example.demo.model.DeptUser;
import com.example.demo.repository.DeptUserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataItemWriter implements ItemWriter<DeptUser> {

    @Autowired
    private DeptUserRepository deptUserRepository;

    @Override
    public void write(List<? extends DeptUser> deptUsers) throws Exception {

        System.out.println("Data Saved for Users: " + deptUsers);
        deptUserRepository.saveAll(deptUsers);
//        deptUserRepository.save(new DeptUser());
    }

}
