package com.payment.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.app.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	public List<Payment> findByOrderId(Long orderId);
}
