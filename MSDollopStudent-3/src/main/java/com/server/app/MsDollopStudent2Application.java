package com.server.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.server.app.entities.Students;
import com.server.app.repository.IStudentRepository;


@SpringBootApplication
@EnableCaching
public class MsDollopStudent2Application {
@Autowired
private IStudentRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(MsDollopStudent2Application.class, args);
		
	}

	//@Override
	public void run(String... args) throws Exception {
		for(int i=0;i<100;i++) {
			Students s = new Students();
			s.setAddress("Indore"+i);
			s.setName("Rohit"+i);
			s.setCoachingId(i);
			s.setCourseId(i+1);
			this.repo.save(s);	
		}
	}

	
}
