/**
 * 
 */
package com.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.model.Result;
import com.demo.model.Student;


/**
 * @author Mehul
**/

@Configuration
public class SpringBatchConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
							StepBuilderFactory stepBuilderFactory,
							ItemReader<Student> itemReader,
							ItemProcessor<Student, Result> itemProcessor,
							ItemWriter<Result> itemWriter,
							ItemWriter<Student> itemWriter2) {
		
		Step step2 = stepBuilderFactory
				.get("Student-marks-load")
				.<Student,Student>chunk(100)
				.reader(itemReader)
				.writer(itemWriter2)
				.build();
		
		Step step1 = stepBuilderFactory
				.get("Student-result-load")
				.<Student, Result>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory
				.get("Student-marks-read-result-load")
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.next(step2)
				.build();
	}
	
}
