package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entitties.Course;

@Service
public class CourseServiceImplement implements CourseService {
	
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<Course> getCourses() {
		
		return courseDao.findAll();
	}

	@Override
	public Optional<Course> getCourse(long courseId) {
		
		return courseDao.findById(courseId);
	}

	@Override
	public ResponseEntity<HttpStatus> addCourse(Course course) {
		
		//if a course already exists with the given id, the course cant be added
		if(courseDao.existsById(course.getId()))
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//else course successfully added
		courseDao.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}

	@Override
	public ResponseEntity<HttpStatus> editCourse(Course course) {
		
		//if course with given id found, edit the course accordingly
		if(courseDao.existsById(course.getId())==false)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		
		//Failed to find course with the given courseId
		courseDao.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@Override
	public void deleteCourse(long courseId) {
		
		Course c=courseDao.getReferenceById(courseId);
		courseDao.delete(c);
		
	}
	
}
