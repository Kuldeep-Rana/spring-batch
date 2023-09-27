package com.example.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FirstJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean(name = "rest-api-first-job")
    public Job firstJob(){
        return jobBuilderFactory.get("first-job-with-rest")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("first-rest-step").tasklet(
                (stepContribution, chunkContext) -> {
            System.out.println("running step 1");
            return RepeatStatus.FINISHED;
        }).build();
    }

}
