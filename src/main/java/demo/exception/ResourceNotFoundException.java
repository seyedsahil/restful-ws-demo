package demo.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9136484699339220860L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
