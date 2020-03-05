/**
 * 
 */
package com.demo.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mehul
**/

public class TestRestTemplate {

	public static void main(String[] args) throws UnexpectedInputException, ParseException, Exception {
		
		List<Student> studentList = new ArrayList<Student>();
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8081/get-students";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
		//System.out.println("response ----- "+response.getBody());
		
		//JSONArray responseJsonArray = new JSONArray(response.getBody());
		System.out.println(jsonItemReader(response.getBody()));
		System.out.println(new ByteArrayResource(response.getBody().getBytes()).contentLength());
		
		 JsonItemReader<Student> s = jsonItemReader(response.getBody());
		 
		 System.out.println(s.toString());
		 System.out.println(s.read().toString());
		 
		/*
		 * for(int i=0; i<responseJsonArray.length(); i++) { JSONObject jsonObject =
		 * responseJsonArray.getJSONObject(i); Student s = new Student();
		 * s.setStudentId(jsonObject.getInt("studentId")); s.setStudentName((String)
		 * jsonObject.get("studentName")); s.setMarks1(jsonObject.getInt("marks1"));
		 * s.setMarks2(jsonObject.getInt("marks2"));
		 * s.setMarks3(jsonObject.getInt("marks3")); studentList.add(s); }
		 * System.out.println(studentList.toString());
		 */
	}
	
	public static JsonItemReader<Student> jsonItemReader(String response) {

		   ObjectMapper objectMapper = new ObjectMapper();
		   // configure the objectMapper as required
		   JacksonJsonObjectReader<Student> jsonObjectReader =  new JacksonJsonObjectReader<>(Student.class);
		   jsonObjectReader.setMapper(objectMapper);
		   Resource resource = new ByteArrayResource(response.getBytes());
		   return new JsonItemReaderBuilder<Student>()
		                 .jsonObjectReader(jsonObjectReader)
		                 .resource(resource)
		                 .name("studentJsonItemReader")
		                 .build();
		}
	
	
	// (Resource) new ByteArrayInputStream(response.getBytes())
}
