package com.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SpringBatchConsumerStudentsMarksApplication {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchConsumerStudentsMarksApplication.class, args);
	}
	
	//@Scheduled(cron = "* * * ? * *") // for every second
	@Scheduled(cron = "0 * * ? * *") // for every min
	public void load() throws JobExecutionAlreadyRunningException, 
				JobRestartException, 
				JobInstanceAlreadyCompleteException, 
				JobParametersInvalidException {
		
		Map<String, JobParameter> maps = new HashMap<String, JobParameter>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		
		JobParameters parameter = new JobParameters(maps);
		
		jobLauncher.run(job, parameter);
	}

}
