package it.epicode.be.library.util.exception;

public class BookNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -6952327960352830402L;

	public BookNotFoundException(String message) {
		super(message);
	}

}
