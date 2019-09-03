package com.example.climate.scheduler;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.climate.service.MongoDBService;

@Component
public class ClimateTask {
	 
	private static final Logger log = LoggerFactory.getLogger(ClimateTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
   

    @SuppressWarnings("deprecation")
	@Scheduled(fixedRate = 35000)
    public void reportCurrentTime() {
    	log.info("The time is now ===##########--->{}", dateFormat.format(new Date()));
    	 MongoDBService mongoDBService = new MongoDBService();
    	mongoDBService.deleteRecordGrt3Days();
    }

}
