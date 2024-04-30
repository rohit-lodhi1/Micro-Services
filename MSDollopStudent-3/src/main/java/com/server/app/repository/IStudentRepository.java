package com.server.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.app.entities.Students;

public interface IStudentRepository extends JpaRepository<Students,Integer>{

	public List<Students> findByCoachingId(Integer id);
	public List<Students> findByCourseId(Integer id);
	
	public Page<Students> findByIsDeleted(boolean deleted,Pageable page); 
	
}
