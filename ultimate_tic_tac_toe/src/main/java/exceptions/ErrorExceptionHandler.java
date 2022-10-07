package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorExceptionHandler {
	
	@ExceptionHandler(value = {RequestException.class})
	public ResponseEntity<Object> handleRequestException(RequestException e) {
		Exception Exception = new Exception(e.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(Exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value = {RequestException.class})
	public ResponseEntity<Object> handleRequestException1(RequestException e) {
		Exception Exception = new Exception(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(Exception,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
