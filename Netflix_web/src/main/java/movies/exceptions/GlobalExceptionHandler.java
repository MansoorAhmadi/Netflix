package movies.exceptions;

import movies.errorMessages.ErrorMessage;
import movies.internalServerError.InternalServerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessage>
    internalServerError(Exception exception, WebRequest request) {
        ErrorMessage errors =
                new ErrorMessage(500, new Date(), exception.getMessage(), "Internal Server Error!");

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> userNotFound(Exception exception, WebRequest request) {
        ErrorMessage errors =
                new ErrorMessage(404, new Date(), exception.getMessage(), "User Not Found!");

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
