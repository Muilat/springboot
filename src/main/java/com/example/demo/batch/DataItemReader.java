package com.example.demo.batch;

import com.example.demo.model.DeptUser;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//@Component

public class DataItemReader extends FlatFileItemReader {

//    final
//    Resource resource;
//
//    public DataItemReader(@Value("${input}") Resource resource) {
//        this.resource = resource;
//    }
//
//    @Override
//    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        FlatFileItemReader<DeptUser> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(resource);
//        flatFileItemReader.setName("CSV-Reader");
//        flatFileItemReader.setLinesToSkip(1);
//        flatFileItemReader.setLineMapper(lineMapper());
//        return flatFileItemReader;
//    }
//
//
//    @Bean
//    public LineMapper<DeptUser> lineMapper() {
//
//        DefaultLineMapper<DeptUser> defaultLineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames(new String[]{"id", "name", "dept", "salary"});
//
//        BeanWrapperFieldSetMapper<DeptUser> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(DeptUser.class);
//
//        defaultLineMapper.setLineTokenizer(lineTokenizer);
//        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
//
//        return defaultLineMapper;
//    }



}
