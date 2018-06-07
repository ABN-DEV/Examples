/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abndev.example.beans.User;
import com.abndev.example.dao.UserService;
import com.abndev.example.repo.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertNotNull;

// static import MockMvcRequestBuilders.*;
// static import MockMvcResultMatchers.*

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
public class UserControllerJpaTests extends AbstractTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepositoryCreatedTest() {

        assertNotNull( "UserRepository not created.", userRepository );
    }

}
