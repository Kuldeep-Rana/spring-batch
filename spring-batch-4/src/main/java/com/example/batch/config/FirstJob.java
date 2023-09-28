package com.example.batch.config;

import com.example.batch.reader.UserCSVReader;
import com.example.batch.request.UserCsv;
import com.example.batch.writer.UserCsvWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FirstJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UserCsvWriter userCsvWriter;
    private final UserCSVReader userCSVReader;

    @Bean
    public Job csvJob(){
        return jobBuilderFactory.get("csv-job")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("csv-step")
                .<UserCsv,UserCsv>chunk(100)
                .reader(userCSVReader.userCsvFlatFileItemReader())
                .writer(userCsvWriter)
                .build();
    }

}
