package com.stock.app.exception;

public class EmptyInventoryException extends RuntimeException {

	public EmptyInventoryException() {
		super("Inventory is Empty");
	}

	public EmptyInventoryException(String msg) {
        super(msg);
	}
}
