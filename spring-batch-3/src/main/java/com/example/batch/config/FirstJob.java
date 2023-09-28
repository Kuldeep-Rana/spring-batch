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
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FirstJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean(name = "first-job-with-scheduler")
    public Job firstJob(){
        return jobBuilderFactory.get("first-job-with-scheduler")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("first-scheduler-step")
                .<Integer,Long>chunk(4)
                .reader(reader())
                .processor((ItemProcessor<Integer, Long>) i -> Long.valueOf(i*10))
                .writer(list -> System.out.println("writing "+list))
                .build();
    }

    private List<Integer> data = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
    private int counter = 0;
    private ItemReader<Integer> reader(){
        return () -> {
            if(counter >= data.size())
                return null;
            return data.get(counter++);
        };
    }

}
