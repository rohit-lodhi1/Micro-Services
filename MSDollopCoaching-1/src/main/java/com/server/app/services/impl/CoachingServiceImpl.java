package com.server.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.app.entities.Coaching;
import com.server.app.entities.Course;
import com.server.app.entities.dto.CoachingDto;
import com.server.app.entities.dto.CourseDto;
import com.server.app.fiegnClient.CourseFeignClient;
import com.server.app.fiegnClient.StudentsFeignClient;
import com.server.app.repository.CoachingRepository;
import com.server.app.services.CoachingService;

@Service
public class CoachingServiceImpl implements CoachingService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CourseFeignClient courseFeignClient;
	
	@Autowired
	private StudentsFeignClient studentsFeignClient;
	
	
	@Autowired
	private CoachingRepository coachingRepository;

	
	@Override
	public CoachingDto createCoaching(CoachingDto coachingDto) {
		Coaching coaching = this.coachingRepository.save(this.coachingDtoToCoaching(coachingDto));
		
		return this.coachingToCoachingDto(coaching);
	}

	
	@Override
	public CoachingDto getCoaching(Integer id) {
		  Optional<Coaching> coaching = this.coachingRepository.findById(id);
		 coaching.get().setCourses(this.courseFeignClient.getCourseOfCoaching(id).getBody());
		 coaching.get().setStudents(this.studentsFeignClient.getAllStudentsByCoachingId(id).getBody());
		return this.coachingToCoachingDto(coaching.get());
	}

	@Override
	public CoachingDto updateCoaching(CoachingDto coachingDto) {
Coaching coaching = this.coachingRepository.save(this.coachingDtoToCoaching(coachingDto));
		
		return this.coachingToCoachingDto(coaching);
		
	}

	@Override
	public List<CoachingDto> getAllCoachings() {
		List<Coaching> list = this.coachingRepository.findAll();
		List<CoachingDto> listDto = new ArrayList<>();
		list.forEach(coaching->{
			listDto.add(this.coachingToCoachingDto(coaching));
		});
		return listDto;
	}
	
	private Coaching coachingDtoToCoaching(CoachingDto coachingDto) {
		return this.mapper.map(coachingDto, Coaching.class);
	}
	private CoachingDto coachingToCoachingDto(Coaching coaching) {
		return this.mapper.map(coaching, CoachingDto.class);
	}
	

	private CourseDto courseToCourseDto(Course course) {
		return this.mapper.map(course, CourseDto.class);
	}
	private Course courseDtoToCourse(CourseDto courseDto) {
		return this.mapper.map(courseDto, Course.class);
	}
	
}
