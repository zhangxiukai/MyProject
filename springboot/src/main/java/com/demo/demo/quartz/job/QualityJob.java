package com.demo.demo.quartz.job;

import com.demo.demo.service.QualityService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class QualityJob implements Job {

    private Logger logger = LoggerFactory.getLogger(QualityJob.class);

    @Autowired
    QualityService qualityService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        logger.info(jobKey.getName());
        logger.info(jobKey.getGroup());
        qualityService.doJob();
    }
}
