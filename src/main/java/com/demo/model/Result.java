/**
 * 
 */
package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.demo.enums.Grades;
import com.demo.enums.PerformanceGrade;

import lombok.Data;

/**
 * @author Mehul
**/

@Entity
@Table(name = "result_master")
@Data
public class Result {

	@Id
	@GeneratedValue
	private int resultId;
	
	/*
	 * @OneToOne(targetEntity = Student.class)
	 * 
	 * @JoinColumn(name = "studentId")
	 */
	private int studentId;
	
	private int totalMarks;
	private int totalObtainedMarks;
	private float percentages;
	
	@Enumerated(EnumType.STRING)
	private Grades gradeForSub1;
	
	@Enumerated(EnumType.STRING)
	private Grades gradeForSub2;
	
	@Enumerated(EnumType.STRING)	
	private Grades gradeForSub3;
	
	@Enumerated(EnumType.STRING)
	private  PerformanceGrade overallPerformance;
	
	private int noOfSubjects;
	private int noOfPassSubjects;
	private int noOfFailSubjects;
	private boolean isOverallPass;
	
	@Column
	private Date created;
	
	@Column
	private Date updated;
	
	@PrePersist
	  protected void onCreate() {
	    created = new Date();
	  }
	
	@PreUpdate
	  protected void onUpdate() {
	    updated = new Date();
	  }
}
