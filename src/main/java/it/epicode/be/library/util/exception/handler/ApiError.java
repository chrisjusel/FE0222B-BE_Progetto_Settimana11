/**
 * Modello degli errori inviati come response
 */
package it.epicode.be.library.util.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiError {
	private final String message;
	private final HttpStatus status;
}