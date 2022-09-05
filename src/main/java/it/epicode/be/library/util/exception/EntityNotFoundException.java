package it.epicode.be.library.util.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1891042371754243338L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
