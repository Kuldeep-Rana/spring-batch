package com.example.batch.reader;

import com.example.batch.request.UserCsv;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UserCSVReader {
    public FlatFileItemReader<UserCsv> userCsvFlatFileItemReader(){
        var reader = new FlatFileItemReader<UserCsv>();
        reader.setLinesToSkip(1);
        reader.setResource(new FileSystemResource(new File("input/users.csv")));

        var lineMapper =  new DefaultLineMapper<UserCsv>();
        var tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name","email","dob","phone","address");
        lineMapper.setLineTokenizer(tokenizer);
        var fieldSetMapper = new BeanWrapperFieldSetMapper<UserCsv>();
        fieldSetMapper.setTargetType(UserCsv.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);
        return reader;
    }

}
