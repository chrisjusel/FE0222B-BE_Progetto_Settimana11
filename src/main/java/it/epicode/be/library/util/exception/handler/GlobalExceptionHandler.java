/**
 * Questo gestore di eccezioni è stato implementato utilizzando delle eccezioni
 * generiche in modo che tutte le eccezioni che saranno sottoclassi delle eccezioni
 * qui utilizzate saranno gestite in questo modo: creando un oggetto che 
 * conterrà il messaggio di errore e lo stato http che poi verrà
 * inviato come response 
 */
package it.epicode.be.library.util.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.epicode.be.library.util.exception.EntityNotFoundException;
import it.epicode.be.library.util.exception.LibraryException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(LibraryException.class)
	protected ResponseEntity<Object> handleLibraryException(LibraryException ex) {

		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {

		ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);

		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
