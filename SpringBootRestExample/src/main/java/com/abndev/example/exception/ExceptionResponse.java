/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-04<br>
 * <br>
 */
package com.abndev.example.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Exception response message.
 * 
 * @since 2018.06.04
 * @author annik
 */
public class ExceptionResponse {

    private LocalDateTime timestamp;

    private String message;

    private String details;

    /**
     * Contructor.
     * 
     * @param timestamp
     * @param message
     * @param details
     */
    public ExceptionResponse( LocalDateTime timestamp, String message, String details ) {

        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {

        return timestamp;
    }

    public void setTimestamp( LocalDateTime timestamp ) {

        this.timestamp = timestamp;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage( String message ) {

        this.message = message;
    }

    public String getDetails() {

        return details;
    }

    public void setDetails( String details ) {

        this.details = details;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append( "ExceptionResponse [timestamp=" );
        builder.append( timestamp );
        builder.append( ", message=" );
        builder.append( message );
        builder.append( ", details=" );
        builder.append( details );
        builder.append( "]" );
        return builder.toString();
    }

}
