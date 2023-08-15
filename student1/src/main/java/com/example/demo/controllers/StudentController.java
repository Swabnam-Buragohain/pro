package com.example.demo.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentDAO;


@RestController
public class StudentController {

	@Autowired
	StudentDAO dao;
	
	@GetMapping("/students")			// 		GET		http://localhost:8686/employees
	public List<Student> getStudent()
	{
		return dao.findAll();
	}
	
	@GetMapping("/student/{id}")		//		GET		http://localhost:8686/22
	public Optional<Student> getStudent(@PathVariable int id)
	{
		return dao.findById(id);
	}
	
	@PostMapping("/student")			// 		POST	http://localhost:8686/employee
	public String addStudent(@RequestBody Student e)
	{
		if(dao.existsById(e.getEid()))
			return "Student already exist with given id";
		dao.save(e);
		return "Student added successfully....";
	}
	
	@DeleteMapping("/student")			//		DELETE	http://localhost:8686/employee?id=33
	public String removeStudent(@RequestParam int id)
	{
		if(dao.existsById(id))
		{
			dao.deleteById(id);
			return "Successfully removed student.....";
		}
		return "Sorry ! No record with given id to delete";
	}
	
	@PutMapping("/student")			// 		PUT		http://localhost:8686/employee
	public String updateStudent(@RequestBody Student e)
	{
		if(dao.existsById(e.getEid()))
		{
			dao.save(e);
			return "Updated successfully.....";
		}
		return "No record with given id to update";
	}
	
	@GetMapping("/students/desig")		//		GET		http://localhost:8686/employees/desig?role=Manager
	public List<Student> getStudentsBasedOnDesignation(@RequestParam String role)
	{
		return dao.findByDesignation(role);
	}
	
	@GetMapping("/employees/above")		//		GET 	http://localhost:8686/employees/above?age=40
	public List<Student> getStudentsAboveAge(@RequestParam int age)
	{
		return dao.findByAgeGreaterThan(age);
	}

	@GetMapping("/students/below")		//		GET 	http://localhost:8686/employees/below?age=40
	public List<Student> getStudentsBelowAge(@RequestParam int age)
	{
		return dao.findByAgeLessThan(age);
	}
	
	@GetMapping("/students/sorted")	//http://localhost:8686/employees/sorted?role=Manager&age=20
	public List<Student> getStudentInSortedOrder(@RequestParam String role,@RequestParam int age)
	{
		return dao.myCustomQuery(role,age);
	}
}
