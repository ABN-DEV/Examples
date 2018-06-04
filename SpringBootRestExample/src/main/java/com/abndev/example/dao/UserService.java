/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.dao;

import com.abndev.example.beans.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@Service
public class UserService {

    /**
     * Find all {@link User}s.
     * 
     * @return
     */
    public List<User> findAllUsers() {

        List<User> all = new ArrayList<User>();
        return all;
    }

    /**
     * Find one {@link User}.
     * 
     * @param id
     * @return {@link User} with id or null.
     */
    public Optional<User> findOne( int id ) {

        return Optional.empty();
    }

    /**
     * Save {@link User}.
     * 
     * @param user
     * @return
     */
    public User save( User user ) {

        // TODO Auto-generated method stub
        return user;
    }

    public User deleteById( long id ) {

        User user = null;
        
        return user;
    }

}
