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
public class UserControllerTests extends AbstractTests {

    @Autowired
    @MockBean
    private UserService userServiceMock;

    @Autowired
    private UserRepository userRepository;

    /**
     * @throws Exception
     */
    @Test
    public void retrieveAllUsersUrlStatusIsOkTest() throws Exception {

        mockMvc.perform( get( "/api/users" ) )
            .andExpect( status().isOk() );
    }

    @Test
    public void retrieveAllUsersMockedTest() throws Exception {

        User found1 = new User( 1, "IVAN", "GOVNOV", LocalDate.of( 1990, 3, 21 ) );
        User found2 = new User( 2, "IVAN2", "GOVNOV2", LocalDate.of( 1994, 4, 24 ) );

        List<User> foundAll = new ArrayList<User>();
        foundAll.add( found1 );
        foundAll.add( found2 );

        when( userServiceMock.findAllUsers() ).thenReturn( foundAll );

        StringBuilder jsonContent = new StringBuilder();
        jsonContent.append( "[" )
            .append( "{" )
            .append( "\"gid\":1," )
            .append( "\"firstname\":\"IVAN\"," )
            .append( "\"lastname\":\"GOVNOV\"," )
            .append( "\"birthDate\":[1990,3,21]" )
            .append( "},{" )
            .append( "\"gid\":2," )
            .append( "\"firstname\":\"IVAN2\"," )
            .append( "\"lastname\":\"GOVNOV2\"," )
            .append( "\"birthDate\":[1994,4,24]" )
            .append( "}]" );

        mockMvc.perform( get( "/api/users" ) )
            .andExpect( status().isOk() )
            .andExpect( content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
            .andExpect( content().json( jsonContent.toString(), false ) );

    }

    @Test
    @Ignore
    public void retrieveOneUserTest() throws Exception {

        User newUser = new User( null, "IVAN3", "GOVNOV3", LocalDate.of( 1990, 3, 21 ) );

        User createdUser = new User( -1, "IVAN3", "GOVNOV3", LocalDate.of( 1990, 3, 21 ) );

        when( userServiceMock.save( newUser ) ).thenReturn( createdUser );

        StringBuilder jsonContent = new StringBuilder();
        jsonContent.append( "{" )
            .append( "\"firstname\":\"IVAN3\"," )
            .append( "\"lastname\":\"GOVNOV3\"," )
            .append( "\"birthDate\":[1990,3,21]" )
            .append( "}" );

        StringBuilder contentResponse = new StringBuilder();
        contentResponse.append( "{" )
            .append( "\"gid\":1," )
            .append( "\"firstname\":\"IVAN\"," )
            .append( "\"lastname\":\"GOVNOV\"," )
            .append( "\"birthDate\":[1990,3,21]" )
            .append( "}" );

        mockMvc.perform( post( "/api/users" ).contentType( MediaType.APPLICATION_JSON )
            .content( jsonContent.toString() ) )
            .andExpect( status().isCreated() )
            .andExpect( content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) )
            .andExpect( content().json( contentResponse.toString(), false ) );
    }

    /**
     * User Not Found Exception - TODO
     * 
     * @throws Exception
     */
    @Test
    public void userNotFoundExceptionTest() throws Exception {

        mockMvc.perform( get( "/api/users/{id}", -111L ) )
            .andExpect( status().isNotFound() )
            .andExpect( content().contentTypeCompatibleWith( MediaType.APPLICATION_JSON ) );
    }

}
