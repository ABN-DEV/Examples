/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.rest;

import com.abndev.example.beans.User;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@RestController
@RequestMapping( value = "/users" )
public class UserController {

    /**
     * Retrive All Users
     */
//    TODO : checkout Page Users or just first 1000 users only 
    @GetMapping( value = {"", "/"} )
    public List<User> retrieveAllUsers() {

        return null;
    }

    /**
     * Retrieve {@link User} by his gid.
     * 
     * @param id
     *            of {@link User}
     */
    @GetMapping( "/{id}" )
    public User retrieveUser( int id ) {

        return null;
    }

}
