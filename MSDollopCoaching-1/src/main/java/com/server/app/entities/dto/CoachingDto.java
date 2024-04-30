package com.server.app.entities.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.server.app.entities.Course;
import com.server.app.entities.Students;

import lombok.Data;

@Data
public class CoachingDto {
private Integer id;
	
	private String name;
	
	private String location;
	
	private String code;
	
	@Transient
	private List<CourseDto> courses;
	
	@Transient
	private List<Students> students;
}
