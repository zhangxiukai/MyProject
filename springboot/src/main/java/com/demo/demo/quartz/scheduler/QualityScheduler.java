package com.demo.demo.quartz.scheduler;

import static org.quartz.TriggerBuilder.newTrigger;

import com.demo.demo.entity.QualityPlan;
import com.demo.demo.quartz.job.QualityJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class QualityScheduler implements JobListener {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private Logger logger = LoggerFactory.getLogger(QualityScheduler.class);

    public void addJobToScheduler(QualityPlan qualityPlan) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // build jobDetail
        JobDetail jobDetail = JobBuilder.newJob(QualityJob.class)
            .withIdentity(getJobKey(qualityPlan)).build();
        Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
        scheduler.getListenerManager().addJobListener(this, matcher);

        if ("once".equals(qualityPlan.getSchedulerType())) {
            SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity(qualityPlan.getId())
                .startAt(qualityPlan.getStartTime()).build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info(
                "the job will be scheduled at :" + trigger.getStartTime() + " and repeat once");
        } else {
            CronTrigger trigger = newTrigger().withIdentity(qualityPlan.getId())
                .startAt(qualityPlan.getStartTime())
                .endAt(qualityPlan.getEndTime()).withSchedule(
                    CronScheduleBuilder.cronSchedule(qualityPlan.getCron())
                        .withMisfireHandlingInstructionDoNothing()).build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("the job will be scheduled at :" + trigger.getStartTime() + " and end at :"
                + trigger.getEndTime() + " repeat by :" + trigger.getCronExpression());
        }

    }

    private JobKey getJobKey(QualityPlan qualityPlan) {
        return JobKey.jobKey(qualityPlan.getId());
    }

    @Override
    public String getName() {
        return "qualityScheduler";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        logger.info("job was to be executed :" + jobExecutionContext.getJobDetail().getKey());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        logger.info("job was vetoed :" + jobExecutionContext.getJobDetail().getKey());
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        logger.info("job was  executed :" + jobExecutionContext.getJobDetail().getKey());
    }
}
