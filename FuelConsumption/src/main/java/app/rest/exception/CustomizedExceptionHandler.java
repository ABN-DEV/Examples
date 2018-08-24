/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handle all exceptions.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * All INternal Errors response.
     */
    @ExceptionHandler( {Exception.class, InternalServerException.class} )
    public final ResponseEntity<Object> handleAllExceptions( Exception ex,
            WebRequest request ) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse( LocalDateTime.now(), ex.getMessage(), request.getDescription( false ) );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    /**
     * 404
     * 
     * @param ex
     *            is the {@link Exception}
     * @param request
     *            the {@link WebRequest}
     * @return
     */
    @ExceptionHandler( DriverNotFoundException.class )
    public final ResponseEntity<Object> handleUserNotFoundExceptions( Exception ex,
            WebRequest request ) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse( LocalDateTime.now(), ex.getMessage(), request.getDescription( false ) );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.NOT_FOUND );
    }

    /**
     * 500 error
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler( DuplicateRecordsException.class )
    public final ResponseEntity<Object> handleDuplicateRecordsException( Exception ex,
            WebRequest request ) {

        ExceptionResponse exceptionResponse = new ExceptionResponse( LocalDateTime.now(),
            "Posible error is Duplicate records. " + ex.getMessage(),
            request.getDescription( false ) );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    /**
     * 400
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request ) {

        ExceptionResponse exceptionResponse = new ExceptionResponse( LocalDateTime.now(),
            "Validation failed.",
            ex.getBindingResult()
                .toString() );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.BAD_REQUEST );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable( HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request ) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse( LocalDateTime.now(), "Message not readable. ", ex.getMessage() );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.BAD_REQUEST );
    }

}
