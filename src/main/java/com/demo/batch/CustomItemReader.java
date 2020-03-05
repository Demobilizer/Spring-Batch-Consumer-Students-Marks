/**
 * 
 */
package com.demo.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.model.Student;
import com.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mehul
 **/

@Component
public class CustomItemReader {

	@Autowired
	private StudentService studentService;

	@Bean
	@StepScope
	public JsonItemReader<Student> jsonItemReader() throws IOException {

		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8081/get-students";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		// configure the objectMapper as required
		JacksonJsonObjectReader<Student> jsonObjectReader = new JacksonJsonObjectReader<>(Student.class);
		jsonObjectReader.setMapper(objectMapper);
		
		List<Student> stuFromDb = studentService.getAllStudents();
		List<Student> newStuFromProvider = new ArrayList<>();
		List<Student> allStuFromProvider = new ArrayList<>();
		
		Resource resource = null;
		
		if (stuFromDb.isEmpty()) {
			resource = new ByteArrayResource(response.getBody().getBytes());
		} else {
			JSONArray responseJsonArray = new JSONArray(response.getBody());

			for (int i = 0; i < responseJsonArray.length(); i++) {
				JSONObject jsonObject = responseJsonArray.getJSONObject(i);
				Student s = new Student();
				s.setStudentId(jsonObject.getInt("studentId"));
				s.setStudentName((String) jsonObject.get("studentName"));
				s.setMarks1(jsonObject.getInt("marks1"));
				s.setMarks2(jsonObject.getInt("marks2"));
				s.setMarks3(jsonObject.getInt("marks3"));

				allStuFromProvider.add(s);
			}

			List<Integer> providedStudentIds = new ArrayList<>();
			List<Integer> dbStudentsIds = new ArrayList<>();
			HashMap<Integer, Student> providedStuMap = new HashMap<>();

			for (Student stu : stuFromDb) {
				dbStudentsIds.add(stu.getStudentId());
			}
			for (Student stu : allStuFromProvider) {
				providedStudentIds.add(stu.getStudentId());
				providedStuMap.put(stu.getStudentId(), stu);
			}

			providedStudentIds.removeAll(dbStudentsIds);

			for (Integer id : providedStudentIds) {
				newStuFromProvider.add(providedStuMap.get(id));
			}

			JSONArray jsArray = new JSONArray(newStuFromProvider);
			resource = new ByteArrayResource(jsArray.toString().getBytes());
		}
		
		return new JsonItemReaderBuilder<Student>()
				.jsonObjectReader(jsonObjectReader)
				.resource(resource)
				.name("Student marks reader")
				.build();
	}

}

