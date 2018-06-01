/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-05-30<br>
 * <br>
 */
package com.abndev.example.rest;

import com.abndev.example.beans.StatusServiceBean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @since 2018.05.30
 * @author annik
 */
@RestController
@RequestMapping(value="/api/")
public class StatusServiceController {

    /**
     * @return
     */
    @GetMapping( value = "/status" )
    public StatusServiceBean statusService() {

        return new StatusServiceBean();
    }
}
