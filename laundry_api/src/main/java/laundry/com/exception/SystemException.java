package laundry.com.exception;

public class SystemException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemException() {
		
	}
	
	public SystemException(String message) {
		this(message, null);
	}
	
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
