package com.example.batch.config;

import com.example.batch.listener.FirstJobListener;
import com.example.batch.listener.StepListener;
import com.example.batch.service.SecondTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SecondTasklet secondTasklet;
    private final FirstJobListener firstJobListener;
    private final StepListener stepListener;
    @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("first-job").start(step1()).next(step2())
                .incrementer(new RunIdIncrementer()) // this is to increment the job run id to make the params unique always
                .listener(firstJobListener)
                .build();
    }

    private Step step1(){
        return stepBuilderFactory.get("first-step")
                .tasklet(task1())
                .listener(stepListener)
                .build();
    }

    private Step step2(){
        return stepBuilderFactory.get("second-step").tasklet(secondTasklet).build();
    }

    private Tasklet task1() {
        return (contribution, chunkContext) -> {
            System.out.println("this is the first task");
            return RepeatStatus.FINISHED;
        };
    }
}
