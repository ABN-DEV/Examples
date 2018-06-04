/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-04<br>
 * <br>
 */
package com.abndev.example.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Applied to all controllers our Customized Response message.
 * 
 * @since 2018.06.04
 * @author annik
 */
@ControllerAdvice
@RestController
public class CustomizedResponseExnityExceptionHandler extends ResponseEntityExceptionHandler {

    /*
     * All INternal Errors response.
     */
    @ExceptionHandler( Exception.class )
    public final ResponseEntity<Object> handleAllExceptions( Exception ex,
            WebRequest request ) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse( LocalDateTime.now(), ex.getMessage(), request.getDescription( false ) );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler( UserNotFoundException.class )
    public final ResponseEntity<Object> handleUserNotFoundExceptions( Exception ex,
            WebRequest request ) {

        ExceptionResponse exceptionResponse =
            new ExceptionResponse( LocalDateTime.now(), ex.getMessage(), request.getDescription( false ) );

        return new ResponseEntity<Object>( exceptionResponse, HttpStatus.NOT_FOUND );
    }

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

}
