package br.com.fintech.DBException;

public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException() {
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	/**
	 * @param message
	 * @param cause
	 */
	public DBException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public DBException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public DBException(Throwable cause) {
		super(cause);

	}

	
}
