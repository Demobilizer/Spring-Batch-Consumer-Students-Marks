/**
 * 
 */
package com.demo.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.Student;
import com.demo.repository.StudentRepo;

/**
 * @author Mehul
**/

@Component
public class CustomStudentDbWriter implements ItemWriter<Student>{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public void write(List<? extends Student> items) throws Exception {
		
		studentRepo.saveAll(items);
		System.out.println("saved data to Students ----- "+items);
	}
	
}
