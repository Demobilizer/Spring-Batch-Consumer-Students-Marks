/**
 * 
 */
package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Result;

/**
 * @author Mehul
**/

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {

}
