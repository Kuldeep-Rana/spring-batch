package com.example.batch.config;

import com.example.batch.processor.PersonItemProcessor;
import com.example.batch.reader.PersonCSVReader;
import com.example.batch.request.Person;
import com.example.batch.writer.PersonJpaItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class CsvToDbUsingChunkAndPartitioningJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PersonCSVReader personCSVReader;
    private final PersonJpaItemWriter personJpaItemWriter;
    private final PersonItemProcessor processor;


    @Bean
    public Job importJob() {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .start(masterStep())
                .build();
    }
    private Step masterStep() {
        return stepBuilderFactory.get("masterStep")
                .partitioner("step", partitioner())
                .step(step())
                .gridSize(4) // Number of parallel partitions
                .taskExecutor(taskExecutor())
                .build();
    }

    private TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Minimum number of threads to keep alive
        executor.setMaxPoolSize(10); // Maximum number of threads to allow in the pool
        executor.setThreadNamePrefix("spring_batch_async-");
        executor.initialize();
        return executor;
    }

    private Partitioner partitioner() {
        MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
        partitioner.setResources(new FileSystemResource[] {
                new FileSystemResource("input/persons.csv")
        });
        return partitioner;
    }

    private Step step() {
        return stepBuilderFactory.get("step")
                .<Person, Person>chunk(10)
                .reader(personCSVReader.userCsvFlatFileItemReader())
                .processor(processor)
                .writer(personJpaItemWriter)
                .build();
    }


}
