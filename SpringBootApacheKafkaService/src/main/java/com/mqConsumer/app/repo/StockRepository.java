package com.mqConsumer.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mqConsumer.app.entity.StockInfo;

public interface StockRepository extends JpaRepository<StockInfo, Integer>{

}
