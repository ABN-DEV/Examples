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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@Service
public class UserService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 5;

    static {
        users.add( new User( 1, "IVAN", "PETROV", LocalDate.of( 1970, 1, 1 ) ) );
        users.add( new User( 2, "ANDREY", "BARANOV", LocalDate.of( 1971, 2, 2 ) ) );
        users.add( new User( 3, "SERGEY", "KOZLOV", LocalDate.of( 1972, 3, 3 ) ) );
        users.add( new User( 4, "JOHN", "DOE", LocalDate.of( 1973, 4, 4 ) ) );
        users.add( new User( 5, "JACK", "LEVRON", LocalDate.of( 1974, 5, 5 ) ) );
    }

    /**
     * Find all {@link User}s.
     * 
     * @return
     */
    public List<User> findAllUsers() {

        return users;
    }

    /**
     * Find one {@link User}.
     * 
     * @param id
     * @return {@link User} with id or null.
     */
    public Optional<User> findOne( int id ) {

        Optional<User> findFirst = users.stream()
            .filter( t -> t.getGid()
                .equals( id ) )
            .findFirst();

        return findFirst;
    }

    /**
     * Save {@link User}.
     * 
     * @param user
     * @return
     */
    public User save( User user ) {

        if ( user.getGid() == null ) {
            user.setGid( ++userCount );
        }
        users.add( user );

        return user;
    }

    public User deleteById( long id ) {

        User user = null;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            if ( user.getGid()
                .equals( id ) ) {
                users.remove( user );
                break;
            }
        }

        return user;
    }

}
