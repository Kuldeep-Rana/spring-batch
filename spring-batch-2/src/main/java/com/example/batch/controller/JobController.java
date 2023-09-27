package com.example.batch.controller;

import com.example.batch.request.JobParamDTO;
import com.example.batch.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
@EnableAsync
public class JobController {
    private final JobService service;

    @GetMapping("start")
    public String startJob(@RequestParam String jobName, @RequestBody List<JobParamDTO> params) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        System.out.println("starting job: "+jobName);
        service.startJob(jobName,params);
        return "job started";
    }
}
