package db;

public class DbException extends RuntimeException {

	/**
	 * DbException implementation 
	 * Sends the error message to the super class
	 */
	
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}

}
