package com.example.batch.service;

import com.example.batch.request.JobParamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobLauncher launcher;
    @Qualifier("first-job-with-scheduler")
    private final Job firstJob;

    @Async
    public void startJob(String jobName, List<JobParamDTO> params){
        try {
            var jobParam = new HashMap<String,JobParameter>();
            jobParam.put("currentTime", new JobParameter(LocalDateTime.now().toString()));
            params.forEach(param -> {
                jobParam.put(param.getParamName(), new JobParameter(param.getParamValue()));
            });
            var jobParameters = new JobParameters(jobParam);
            if("first-job-with-rest".equals(jobName)){
                var jobExecution = launcher.run(firstJob,jobParameters);
                System.out.println("Running job id "+jobExecution.getId());
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
