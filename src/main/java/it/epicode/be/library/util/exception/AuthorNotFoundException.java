package it.epicode.be.library.util.exception;

public class AuthorNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -5838471049497326252L;

	public AuthorNotFoundException(String message) {
		super(message);
	}

}
