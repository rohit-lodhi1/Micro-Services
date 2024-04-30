package com.server.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.app.entities.Course;
import com.server.app.entities.dto.CourseDto;
import com.server.app.feignClient.StudentsFiegnClient;
import com.server.app.repository.CourseRepository;
import com.server.app.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentsFiegnClient studentsFiegnClient;
	
	@Override
	public CourseDto createCourse(CourseDto courseDto) {
           Course save = this.courseRepository.save(this.courseDtoToCourse(courseDto));
		return this.courseToCourseDto(save);
	}

	@Override
	public CourseDto updateCourse(CourseDto courseDto) {
		   Course save = this.courseRepository.save(this.courseDtoToCourse(courseDto));
			return this.courseToCourseDto(save); 
	}

	@Override
	public CourseDto getCourse(Integer id) {
		Optional<Course> course = this.courseRepository.findById(id);
		CourseDto dto = this.courseToCourseDto(course.get());
		System.out.println(this.studentsFiegnClient.getAllStudentsByCourseId(id).getBody());
		  dto.setStudents(this.studentsFiegnClient.getAllStudentsByCourseId(id).getBody());
		return dto;
	}

	@Override
	public List<CourseDto> getCoursesOfCoaching(Integer id) {
      List<Course> list = this.courseRepository.findByCoachingId(id);
      List<CourseDto> dto = new ArrayList<>();
     
      list.forEach(e->{
    	  e.setStudents(this.studentsFiegnClient.getAllStudentsByCourseId(e.getId()).getBody());
    	     dto.add(this.courseToCourseDto(e));
      });
    
		return dto;
	}

	@Override
	public List<CourseDto> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	private CourseDto courseToCourseDto(Course course) {
		return this.modelMapper.map(course, CourseDto.class);
	}
	private Course courseDtoToCourse(CourseDto courseDto) {
		return this.modelMapper.map(courseDto, Course.class);
	}
}
