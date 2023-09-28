package com.example.batch.service;

import com.example.batch.request.JobParamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final JobService jobService;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void schedule(){
        jobService.startJob("first-job-with-rest", List.of(new JobParamDTO("schedule-key-1","schedule-value-1")));
    }
}
