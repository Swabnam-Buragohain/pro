package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Student;


public interface StudentDAO extends JpaRepository<Student, Integer>{

	public List<Student> findByDesignation(String designation);
	public List<Student> findByAgeGreaterThan(int age);
	public List<Student> findByAgeLessThan(int age);

	@Query("from Student where designation=?1 and age>?2 order by salary desc")
	public List<Student> myCustomQuery(String desig,int a);
	
	
}
