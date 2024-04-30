package com.server.app.entities.dto;

import java.util.List;

import javax.persistence.Transient;

import com.server.app.entities.Students;

import lombok.Data;

@Data
public class CourseDto {
	  private Integer id;
	   
	   private String name;
	   
	  private Integer coachingId;
	   
	   
	   private List<Students> students;
	   
	   private Integer fees;
}
