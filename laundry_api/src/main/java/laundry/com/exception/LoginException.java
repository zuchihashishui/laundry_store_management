package laundry.com.exception;

public class LoginException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		
	}
	
	public LoginException(String message) {
		this(message, null);
	}
	
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
