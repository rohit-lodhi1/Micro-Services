package com.stock.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.app.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	List<Inventory> findByItem(String item);

}
