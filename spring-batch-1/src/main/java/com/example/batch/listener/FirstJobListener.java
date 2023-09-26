package com.example.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("before job: "+jobExecution.getJobInstance().getJobName());
        System.out.println("job params : "+jobExecution.getJobParameters());
        System.out.println("Job execution context: "+jobExecution.getExecutionContext());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("after job: "+jobExecution.getJobInstance().getJobName());
        System.out.println("job params : "+jobExecution.getJobParameters());
        System.out.println("Job execution context: "+jobExecution.getExecutionContext());
    }
}

