package com.example.batch.config;

import com.example.batch.partitioner.SimplePartitioner;
import com.example.batch.reader.SampleItemReader;
import com.example.batch.writer.SampleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemProcessor<String, String> itemProcessor;
    @Autowired
    private ItemReader<String> itemReader;

    @Autowired
    private ItemWriter<String> itemWriter;

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("sampleJob")
                .start(partitionStep())
                .build();
    }

    @Bean
    public Step partitionStep() {
        return stepBuilderFactory.get("partitionStep")
                .partitioner(slaveStep().getName(), new SimplePartitioner())
                .step(slaveStep())
                .gridSize(4) // Number of partitions
                .partitionHandler(partitionHandler())
                .build();
    }

    @Bean
    public Step slaveStep() {
        return stepBuilderFactory.get("slaveStep")
                .<String, String>chunk(2) // Process 10 items at a time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public PartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setStep(slaveStep());
        partitionHandler.setGridSize(4); // Number of partitions
        partitionHandler.setTaskExecutor(new SimpleAsyncTaskExecutor()); // Use a task executor for parallel execution
        return partitionHandler;
    }

}
