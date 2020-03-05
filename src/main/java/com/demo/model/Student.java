/**
 * 
 */
package com.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Mehul
**/

@Entity
@Table(name = "student_master")
@Data
public class Student {

	@Id
	//@GeneratedValue
	private int studentId;
	private String studentName;
	private int marks1;
	private int marks2;
	private int marks3;
	
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
