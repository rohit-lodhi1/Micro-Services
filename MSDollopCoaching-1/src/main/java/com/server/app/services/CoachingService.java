package com.server.app.services;

import java.util.List;

import com.server.app.entities.dto.CoachingDto;

public interface CoachingService {
     
	public CoachingDto createCoaching(CoachingDto coachingDto);
	
	public CoachingDto getCoaching(Integer id);
	
	public CoachingDto updateCoaching(CoachingDto coachingDto);
	
	public List<CoachingDto> getAllCoachings();
	
}
