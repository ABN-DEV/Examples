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
import com.abndev.example.repo.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find all {@link User}s.
     * 
     * @return
     */
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    /**
     * Find one {@link User}.
     * 
     * @param id
     * @return {@link User} with id or null.
     */
    public Optional<User> findOne( int id ) {

        return userRepository.findById( id );
    }

    /**
     * Save {@link User}.
     * 
     * @param user
     * @return
     */
    public User save( User user ) {

        user = userRepository.save( user );
        return user;
    }

    public User deleteById( int id ) {

        User result = null;
        Optional<User> user = userRepository.findById( id );
        if ( user.isPresent() ) {
            result = user.get();
            userRepository.deleteById( (int) id );
        }
        return result;
    }

}
