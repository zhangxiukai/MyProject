package com.demo.demo.controller;

import com.demo.demo.entity.JsonTest;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping(method = RequestMethod.POST, path = "/test")
    public String addQualitySchedule(@RequestBody JsonTest json) throws SchedulerException {
        return "ok";
    }
}
