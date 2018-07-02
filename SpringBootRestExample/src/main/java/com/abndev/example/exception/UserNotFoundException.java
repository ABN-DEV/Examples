/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-04<br>
 * <br>
 */
package com.abndev.example.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * UserNotFound exception.
 * 
 * @since 2018.06.04
 * @author annik
 */
@ResponseStatus( HttpStatus.NOT_FOUND )
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Contructor.
     */
    public UserNotFoundException() {

        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Contructor.
     * 
     * @param message
     */
    public UserNotFoundException( String message ) {

        super( message );
        // TODO Auto-generated constructor stub
    }

}
