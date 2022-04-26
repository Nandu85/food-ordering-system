package com.narola.fooddelivery.dishes;

public class DishException extends RuntimeException {

	public DishException() {
		super();
	}

	public DishException(String message) {
		super(message);
	}

	public DishException(String message, Throwable cause) {
		super(message, cause);
	}

	public DishException(Throwable cause) {
		super(cause);
	}

}
