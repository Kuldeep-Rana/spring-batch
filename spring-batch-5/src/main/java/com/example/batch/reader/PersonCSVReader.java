package com.example.batch.reader;

import com.example.batch.mappr.PersonFieldMapper;
import com.example.batch.request.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PersonCSVReader {
    public FlatFileItemReader<Person> userCsvFlatFileItemReader(){
        var reader = new FlatFileItemReader<Person>();
        reader.setResource(new FileSystemResource(new File("input/persons.csv")));
        reader.setLinesToSkip(1);
        var lineMapper =  new DefaultLineMapper<Person>();
        var tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name","email","dob","phone","address");
        lineMapper.setLineTokenizer(tokenizer);
        //using a custom field mapper
        lineMapper.setFieldSetMapper(new PersonFieldMapper());
        reader.setLineMapper(lineMapper);
        return reader;
    }

}
