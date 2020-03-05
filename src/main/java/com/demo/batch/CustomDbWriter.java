/**
 * 
 */
package com.demo.batch;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.Result;
import com.demo.repository.ResultRepo;

/**
 * @author Mehul
**/

@Component
public class CustomDbWriter implements ItemWriter<Result>{
	
	@Autowired
	private ResultRepo resultRepo;

	@Override
	public void write(List<? extends Result> items) throws Exception {
		
		resultRepo.saveAll(items);
		System.out.println("saved data to result_master ----- "+items);
	}
}
