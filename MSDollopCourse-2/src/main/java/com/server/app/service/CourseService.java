package com.server.app.service;

import java.util.List;

import com.server.app.entities.dto.CourseDto;

public interface CourseService {

	public CourseDto createCourse(CourseDto courseDto);
	
	public CourseDto updateCourse(CourseDto courseDto);
	
	public CourseDto getCourse(Integer id);
	
	public List<CourseDto> getCoursesOfCoaching(Integer id);
	
	public List<CourseDto> getAllCourses();
	
	
}
