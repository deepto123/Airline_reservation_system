package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springrest.springrest.entitties.Course;

public interface CourseService {
	
	public List<Course> getCourses();

	public Optional<Course> getCourse(long courseId);

	public ResponseEntity<HttpStatus> addCourse(Course course);

	public ResponseEntity<HttpStatus> editCourse(Course course);

	public void deleteCourse(long courseId);
}
