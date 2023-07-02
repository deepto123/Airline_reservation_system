package com.springrest.springrest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.entitties.Course;
import com.springrest.springrest.services.CourseService;
import com.springrest.springrest.services.CourseServiceImplement;

@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	
	@Mock
	CourseDao courseDao;
	
	@InjectMocks
	CourseServiceImplement courseService;
	
//	public Optional<Course> course;
	public List<Course>mycourses;
	
	@Test
	@Order(1)
	public void test_getCourses()
	{	
		List<Course>mycourses=new ArrayList<Course>();
		mycourses.add(new Course(234,"CSE","Basics and Foundations of CS"));
		mycourses.add(new Course(239,"Civil","Basics and Foundations of Civil"));
		mycourses.add(new Course(1103,"ECE","Basics and Foundations of Electronics"));
		mycourses.add(new Course(1104,"ECE","Basics and Foundations of Electronics"));
		
		when(courseDao.findAll()).thenReturn(mycourses);//Mocking
		
		assertEquals(4,courseService.getCourses().size());
		
	}
	
//	@Test
//	@Order(2)
//	public void test_getCourse()
//	{	
//		Optional<Course> course;
//		
//		Long courseId=(long) 234;
//		
//		when(courseDao.findById(courseId)).thenReturn(course);
//		
//		assertEquals(courseService.getCourse(courseId).getId(),courseId);
//	}
	
	@Test
	@Order(2)
	public void test_addCourse()
	{	
		Course course1=new Course(1906,"ME","Basics and Foundations of Metallurgy");
		Course course2=new Course(667,"BIOTECHNOLOGY","Basics and Foundations of Biotech");
		

		when(courseDao.save(course1)).thenReturn(course1);//Mocking
		assertEquals(new ResponseEntity<>(HttpStatus.OK),courseService.addCourse(course1));
		
		when(courseDao.existsById(course2.getId())).thenReturn(true);//Mocking
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR),courseService.addCourse(course2));
			
	}
	
	@Test
	@Order(3)
	public void test_editCourse()
	{	
		Course course1=new Course(661,"ME","Basics and Foundations of Metallurgy");
		Course course2=new Course(552,"BIOTECHNOLOGY","Basics and Foundations of Biotech");
		

		when(courseDao.existsById(course1.getId())).thenReturn(true);//Mocking
		assertEquals(new ResponseEntity<>(HttpStatus.OK),courseService.editCourse(course1));
		
		when(courseDao.existsById(course2.getId())).thenReturn(false);//Mocking
		assertEquals(new ResponseEntity<>(HttpStatus.BAD_REQUEST),courseService.editCourse(course2));
			
	}
	
//	@Test
//	@Order(4)
//	public void test_deleteCourse()
//	{
//		Course course;
//		Long courseId=(long) 567;
//
//		when(courseDao.getReferenceById(courseId)).thenReturn(course);//Mocking
//		assertEquals(new ResponseEntity<>(HttpStatus.OK),courseService.deleteCourse(courseId));
//
//	}
	
	
	

}
