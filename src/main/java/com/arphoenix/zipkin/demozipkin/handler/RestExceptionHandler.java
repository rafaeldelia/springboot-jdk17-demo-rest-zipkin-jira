package com.arphoenix.zipkin.demozipkin.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arphoenix.zipkin.demozipkin.exception.RateLimitExceededException;
import com.arphoenix.zipkin.demozipkin.exception.UnauthorizedException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@NonNull
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
			@NonNull HttpStatus status, @NonNull WebRequest request) {
		ResponseMessage error = new ResponseMessage(400, ex.getBindingResult().getFieldErrors().get(0).toString(),
				ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@NonNull
	protected ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, Object body, @NonNull HttpHeaders headers,
			@NonNull HttpStatus status, @NonNull WebRequest request) {
		ResponseMessage error = null;
		if (ex instanceof HttpMessageNotReadableException && (ex.getMessage() != null)) {
			if (ex.getMessage().contains("LocalDateTime") || ex.getMessage().contains("LocalDate")) {
				error = new ResponseMessage(400, ex.getMessage(), "Utilize uma data válida!");
			} else if (ex.getMessage().contains("LocalTime")) {
				error = new ResponseMessage(400, ex.getMessage(), "Utilize horas válidas!");
			}

		}
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	/**
	 * Manipula a exceção UnauthorizedException e retorna uma resposta HTTP 401 (Unauthorized).
	 *
	 * @param ex
	 *            A exceção UnauthorizedException capturada.
	 * @return Uma resposta ResponseEntity contendo as informações de erro.
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
		ResponseMessage error = new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RateLimitExceededException.class)
	public ResponseEntity<Object> handleRateLimitExceededException(RateLimitExceededException ex) {
		ResponseMessage error = new ResponseMessage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value(),
				"O sistema possui restrição de chamadas repetidas.", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}
}