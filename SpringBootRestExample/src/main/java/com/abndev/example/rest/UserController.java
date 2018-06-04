/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.abndev.example.beans.User;
import com.abndev.example.dao.UserService;
import com.abndev.example.exception.UserNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@RestController
@RequestMapping( value = "/api/users" )
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrive All Users
     */
//    TODO : checkout Page Users or just first 1000 users only 
    @GetMapping( value = {""} )
    public List<User> retrieveAllUsers() {

        return userService.findAllUsers();
    }

    /**
     * Retrieve {@link User} by his gid.
     * 
     * @param id
     *            of {@link User}
     * @return
     */
    @GetMapping( "/{id}" )
    public Resource<User> retrieveUser( @PathVariable int id ) {

        User user = null;
        Optional<User> found = userService.findOne( id );
        if ( found.isPresent() ) {
            user = found.get();
        } else {
            throw new UserNotFoundException( "id-" + id );
        }

        // HATEOAS
        Resource<User> resource = new Resource<User>( user );

        ControllerLinkBuilder linkTo = linkTo( methodOn( this.getClass() ).retrieveAllUsers() );
        resource.add( linkTo.withRel( "all-users" ) );

        return resource;
    }

    /**
     * Creating User.
     * 
     * @return
     */
    @PostMapping( value = {""} )
    public ResponseEntity<Object> createUser( @Valid @RequestBody User user ) {

        final User savedUser = userService.save( user );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path( "/{id}" )
            .buildAndExpand( savedUser.getGid() )
            .toUri();

        return ResponseEntity.created( uri )
            .build();
    }

    @DeleteMapping( value = {"/{id}"} )
    public void createUser( @PathVariable long id ) {

        final User user = userService.deleteById( id );

        if ( user == null ) {
            throw new UserNotFoundException( "id-" + id );
        }
    }

}
