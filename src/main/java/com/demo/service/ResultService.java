/**
 * 
 */
package com.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.enums.Grades;
import com.demo.enums.PerformanceGrade;
import com.demo.model.Result;
import com.demo.model.Student;

/**
 * @author Mehul
**/

@Service
public class ResultService {

	@Value("${stu.consumer.totalMarksPerSubject}")
	int totalMarksPerSubject;
	
	@Value("${stu.consumer.totalSubjects}")
	int totalSubjects;
	
	/**
	 * @param student 
	 * @return
	 */
	public Result prepareResult(Student student) {
		
		Result result = new Result();

		int totalObtainedMarks = this.getTotalMarks(student);
		int totalMarks = totalMarksPerSubject * totalSubjects;
		float percentage = this.getPercentage(totalObtainedMarks, totalMarks);
		int noOfPassSub = this.getNoOfPassSubject(student);
		int noOfFailSub = totalSubjects-noOfPassSub;
		boolean isOverallPass = this.isOverAllPass(noOfFailSub);
		//getSubGrade(student.getMarks1());
		//getoverallPerformance(percentage);
		
		result.setTotalMarks(totalMarks);
		result.setTotalObtainedMarks(totalObtainedMarks);
		result.setPercentages(percentage);
		result.setNoOfSubjects(totalSubjects);
		result.setNoOfPassSubjects(noOfPassSub);
		result.setNoOfFailSubjects(noOfFailSub);
		result.setOverallPass(isOverallPass);
		result.setGradeForSub1(getSubGrade(student.getMarks1()));
		result.setGradeForSub2(getSubGrade(student.getMarks2()));
		result.setGradeForSub3(getSubGrade(student.getMarks3()));
		result.setOverallPerformance(getoverallPerformance(percentage));
		result.setStudentId(student.getStudentId());
		
		return result;
	}

	/**
	 * @param percentage
	 * @return 
	 */
	private PerformanceGrade getoverallPerformance(float percentage) {
		PerformanceGrade grade = null;
		if(percentage >= 70) {
			grade = PerformanceGrade.DISTINCTION;
		} else if (percentage >= 60 && percentage <70) {
			grade = PerformanceGrade.FIRST_CLASS;
		} else if (percentage >= 50 && percentage <60) {
			grade = PerformanceGrade.SECOND_CLASS;
		} else if (percentage >= 35 && percentage <50) {
			grade = PerformanceGrade.PASS_CLASS;
		} else {
			grade = PerformanceGrade.FAIL;
		}
		return grade;
	}

	/**
	 * @param marks1
	 * @return 
	 */
	private Grades getSubGrade(int marks) {
		Grades grade = null;
		if(marks >= 85) {
			grade = Grades.AA;
		} else if (marks >= 75 && marks <85) {
			grade = Grades.AB;
		} else if (marks >= 65 && marks <75) {
			grade = Grades.BB;
		} else if (marks >= 55 && marks <65) {
			grade = Grades.BC;
		} else if (marks >= 45 && marks <55) {
			grade = Grades.CC;
		} else if (marks >= 40 && marks <45) {
			grade = Grades.CD;
		} else if (marks >= 35 && marks <40) {
			grade = Grades.DD;
		} else {
			grade = Grades.FF;
		}
		return grade;
		
	}

	/**
	 * @param noOfFailSub
	 * @return
	 */
	private boolean isOverAllPass(int noOfFailSub) {
		if(noOfFailSub == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return
	 */
	private int getNoOfPassSubject(Student student) {
		int count = 0;
		if(student.getMarks1() < 35) {
			count++;
		}
		if(student.getMarks2() < 35) {
			count++;
		}
		if(student.getMarks3() < 35) {
			count++;
		}
		return count;
	}

	/**
	 * @param totalObtainedMarks
	 * @param totalMarks
	 * @return
	 */
	private float getPercentage(int totalObtainedMarks, int totalMarks) {
		float percentage = (totalObtainedMarks*100)/totalMarks;
		return percentage;
	}

	/**
	 * @param student
	 * @return
	 */
	private int getTotalMarks(Student student) {
		return student.getMarks1()+student.getMarks2()+student.getMarks3();
	}

}
