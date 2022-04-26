package com.narola.fooddelivery.location;

public class LocationException extends RuntimeException{
	
	public LocationException() {
        super();
    }

    public LocationException(String message) {
        super(message);
    }

    public LocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationException(Throwable cause) {
        super(cause);
    }
    
}
