package com.server.app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Coaching {

	
	private Integer id;
	
	private String name;
	
	private String location;
	
	private String code;
	
	private List<Course> courses;
	
	private List<Students> students;
	
}
