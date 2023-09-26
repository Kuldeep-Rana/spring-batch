package com.example.batch.config;

import com.example.batch.processor.FirstItemProcessor;
import com.example.batch.reader.FirstItemReader;
import com.example.batch.writer.FirstItemWriter;
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
public class SecondJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final FirstItemWriter firstItemWriter;
    private final FirstItemProcessor firstItemProcessor;
    private final FirstItemReader firstItemReader;

    @Bean
    public Job secondJob(){
        return jobBuilderFactory.get("second-job")
                .incrementer(new RunIdIncrementer()) // this is to increment the job run id to make the params unique always
                .start(checkStep())
                .build();
    }

    private Step checkStep() {
        return stepBuilderFactory.get("chunk-step")
                .<Integer,Long>chunk(2)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }


}
