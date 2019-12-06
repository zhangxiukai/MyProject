package com.demo.demo.controller;

import com.demo.demo.service.QualityService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/scheduler")
public class QualityController {

    @Autowired
    QualityService qualityService;

    @RequestMapping(method = RequestMethod.GET, path = "/once")
    public String addQualitySchedule() throws SchedulerException {
        qualityService.addQualityTask();
        return "ok";
    }
}
