package com.server.app.entities;

import java.util.List;

import javax.persistence.Transient;

public class Course {
	 private Integer id;
	   
	   private String name;
	   
	  private Integer coachingId;
	   
	   @Transient
	   private List<Students> students;
	   
	   private Integer fees;
   
}
