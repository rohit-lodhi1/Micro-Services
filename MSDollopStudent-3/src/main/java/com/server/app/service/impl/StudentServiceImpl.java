package com.server.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.server.app.entities.Students;
import com.server.app.repository.IStudentRepository;
import com.server.app.service.IStudentsService;

@Service
public class StudentServiceImpl implements IStudentsService{

	@Autowired
	private IStudentRepository studentRepo;
	
	
	@Override
	public Students addStudent(Students student) {
		
		return this.studentRepo.save(student);
	}

	@Override
	public Students updateStudent(Students student) {
		// TODO Auto-generated method stub
		return this.studentRepo.save(student);
	}

	@Override
	public Students getStudent(Integer id) {
		// TODO Auto-generated method stub
		return this.studentRepo.findById(id).get();
	}

	@Override
	public List<Students> getAllByCoachingId(Integer coachingId) {
		// TODO Auto-generated method stub
		return this.studentRepo.findByCoachingId(coachingId);
	}

	@Override
	public List<Students> getAllByCourseId(Integer courseId) {
		// TODO Auto-generated method stub
		return this.studentRepo.findByCourseId(courseId);
	}

	@Override
	public Page<Students> getAllStudent(int pageNo,int pageSize) {
	
		return this.studentRepo.findByIsDeleted(false,PageRequest.of(pageNo, pageSize));
	}

	@Override
	public ResponseEntity<?> delete(Integer id) {
		Optional<Students> findById = this.studentRepo.findById(id);
		if(findById.isPresent()) {
			findById.get().setDeleted(true);
			this.studentRepo.save(findById.get());
			this.updateCache(this.getAllStudent(0, 10));
			return  new ResponseEntity<>(HttpStatus.OK);
		}
		return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
	}
    @CachePut(value = "Students", key = "#pageNo + '_' + #pageSize ")
	public Object updateCache( Object newData) {
    	return newData;
	}

	
}
