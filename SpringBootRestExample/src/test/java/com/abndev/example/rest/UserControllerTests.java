/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
//@RunWith( SpringJUnit4ClassRunner.class )
public class UserControllerTests extends AbstractTests {

    /**
     * @throws Exception
     */
    @Test
    public void retrieveAllUsersUrlStatusIsOkTest() throws Exception {

        mockMvc.perform( get( "/users" ) )
            .andExpect( status().isOk() );
    }

}
