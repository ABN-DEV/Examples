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
@ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
public class DuplicateRecordsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Contructor.
     */
    public DuplicateRecordsException() {

        super();
    }

    /**
     * Contructor.
     * 
     * @param message
     */
    public DuplicateRecordsException( String message ) {

        super( message );
    }

    /**
     * Contructor.
     * 
     * @param throwable
     */
    public DuplicateRecordsException( Throwable throwable ) {

        super( throwable );
    }

    /**
     * Contructor.
     * 
     * @param message
     * @param t
     */
    public DuplicateRecordsException( String message, Throwable t ) {

        super( message, t );
    }

}
