package com.consumer.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consumer.app.entity.WikiMediaData;

public interface WikiMediaDataRepository extends JpaRepository<WikiMediaData, Long>{

}
