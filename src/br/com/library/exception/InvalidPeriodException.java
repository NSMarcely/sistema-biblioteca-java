package br.com.library.exception;

public class InvalidPeriodException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidPeriodException(String message) {
		super(message);
		
	}
	public InvalidPeriodException(String msg, Throwable cause){
        super(msg, cause);
    }
	

}
