/**
 * 
 */
package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Student;

/**
 * @author Mehul
**/

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
