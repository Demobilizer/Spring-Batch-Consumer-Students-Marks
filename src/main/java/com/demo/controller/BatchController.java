/**
 * 
 */
package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mehul
**/

@RestController
public class BatchController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	/*
	 * @GetMapping("/load") public void load() throws
	 * JobExecutionAlreadyRunningException, JobRestartException,
	 * JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	 * 
	 * Map<String, JobParameter> maps = new HashMap<String, JobParameter>();
	 * maps.put("time", new JobParameter(System.currentTimeMillis()));
	 * 
	 * JobParameters parameter = new JobParameters(maps);
	 * 
	 * jobLauncher.run(job, parameter); }
	 */
	
}
