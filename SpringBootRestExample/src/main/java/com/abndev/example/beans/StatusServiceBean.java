/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-05-30<br>
 * <br>
 */
package com.abndev.example.beans;

/**
 * Status of service.
 * 
 * @since 2018.05.30
 * @author annik
 */
public class StatusServiceBean {

    public static String statusIsOk = "OK";

    private String message = statusIsOk;

    /**
     * @return "OK"
     */
    public String getMessage() {

        return message;
    }

}
