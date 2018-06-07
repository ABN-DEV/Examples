/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-08<br>
 * <br>
 */
package com.abndev.example.repo;

import com.abndev.example.beans.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @since 2018.06.08
 * @author annik
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
