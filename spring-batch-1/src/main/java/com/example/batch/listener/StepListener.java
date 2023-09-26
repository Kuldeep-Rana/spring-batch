package com.example.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("before step: "+stepExecution.getStepName());
        System.out.println("job params : "+stepExecution.getJobParameters());
        System.out.println("Job execution context: "+stepExecution.getExecutionContext());
        stepExecution.getExecutionContext().put("step-key", "step-value");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("after step: "+stepExecution.getStepName());
        System.out.println("job params : "+stepExecution.getJobParameters());
        System.out.println("Job execution context: "+stepExecution.getExecutionContext());
        return null;
    }
}
