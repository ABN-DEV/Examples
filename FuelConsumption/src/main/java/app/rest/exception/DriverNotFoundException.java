/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Driver not found Exception.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@ResponseStatus( HttpStatus.NOT_FOUND )
public class DriverNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Contructor.
     */
    public DriverNotFoundException() {

        super();
    }

    /**
     * Contructor.
     * 
     * @param message
     */
    public DriverNotFoundException( String message ) {

        super( message );
    }

}
