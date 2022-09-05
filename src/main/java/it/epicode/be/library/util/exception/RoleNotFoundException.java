package it.epicode.be.library.util.exception;

public class RoleNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 6050586507286571333L;

	public RoleNotFoundException(String msg) {
		super(msg);
	}

}
