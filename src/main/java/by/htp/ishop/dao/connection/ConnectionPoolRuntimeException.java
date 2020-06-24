package by.htp.ishop.dao.connection;

public class ConnectionPoolRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolRuntimeException() {

	}

	public ConnectionPoolRuntimeException(String message) {
		super(message);
	}

	public ConnectionPoolRuntimeException(Exception e) {
		super(e);
	}

	public ConnectionPoolRuntimeException(String message, Exception e) {
		super(message, e);
	}
}
