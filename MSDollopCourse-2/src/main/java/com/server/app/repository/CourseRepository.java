package com.server.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.app.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

	public List<Course> findByCoachingId(Integer id);
	
}
