package com.order.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.app.model.Orders;

public interface OrderRepositoy extends JpaRepository<Orders, Long>{

}
