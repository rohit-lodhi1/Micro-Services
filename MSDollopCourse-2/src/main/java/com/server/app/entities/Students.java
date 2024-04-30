package com.server.app.entities;

import lombok.Data;

@Data
public class Students {
private Integer id;
	
	private String name;
	
	private String address;
	
	private Integer courseId;

	private Integer coachingId;
}
