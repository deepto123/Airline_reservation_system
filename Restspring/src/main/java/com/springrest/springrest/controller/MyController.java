package com.springrest.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entitties.Course;
import com.springrest.springrest.services.CourseService;

@RestController	
public class MyController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to courses application";
	}
	
	//get the courses
	@GetMapping("/courses")
	public List<Course>getCourses()
	{
		return this.courseService.getCourses();
	}
	
	//get a course with courseId
	@GetMapping("/courses/{courseId}")
	public Optional<Course> getCourse(@PathVariable String courseId)
	{
		return this.courseService.getCourse(Long.parseLong(courseId));
	}
	
	//add a new course
	@PostMapping("/courses")
	public ResponseEntity<HttpStatus> addCourse(@RequestBody Course course)
	{
		return this.courseService.addCourse(course);
	}
	
	//edit a course
	@PutMapping("/courses")
	public ResponseEntity<HttpStatus> editCourse(@RequestBody Course course) {
	    return this.courseService.editCourse(course);
	}
	
	//delete a course
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId) {
	    
		try
	    {	
			this.courseService.deleteCourse(Long.parseLong(courseId));
	    	return new ResponseEntity<>(HttpStatus.OK);
	    }catch(Exception e) {
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	}
	
	
}
