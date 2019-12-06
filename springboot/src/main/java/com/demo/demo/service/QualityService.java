package com.demo.demo.service;

import com.demo.demo.entity.QualityPlan;
import com.demo.demo.quartz.scheduler.QualityScheduler;
import java.time.Instant;
import java.util.Date;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualityService {

    @Autowired
    private QualityScheduler qualityScheduler;

    public void addQualityTask() throws SchedulerException {
        QualityPlan qualityPlan = new QualityPlan();
        qualityPlan.setId("test-quality-id");
        qualityPlan.setName("quality");
        qualityPlan.setCreateTime(Date.from(Instant.parse("2019-12-02T10:15:30.00Z")));
        qualityPlan.setCreator("zxk");
        qualityPlan.setStartTime(Date.from(Instant.parse("2019-12-02T10:15:30.00Z")));
        qualityPlan.setSchedulerType("once");
        qualityScheduler.addJobToScheduler(qualityPlan);
    }

    public void doJob() {
        System.out.println("----- JOB WAS EXECUTED -----");
        System.out.println("DO SOMETHING");
    }
}
