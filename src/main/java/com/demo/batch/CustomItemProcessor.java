/**
 * 
 */
package com.demo.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.Result;
import com.demo.model.Student;
import com.demo.service.ResultService;

/**
 * @author Mehul
**/

@Component
public class CustomItemProcessor implements ItemProcessor<Student, Result> {

	@Autowired
	private ResultService resultService;
	
	@Override
	public Result process(Student student) throws Exception {

		Result result = resultService.prepareResult(student);
		System.out.println("result from processor ----- "+result.toString());
		return result;
	}

}
