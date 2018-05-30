/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-05-30<br>
 * <br>
 */
package com.abndev.example.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 
 * @since 2018.05.30
 * @author annik
 */
@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringBootTest
public class StatusServiceControllerTest {

    private MockMvc mockMvc;

    /**
     * @throws Exception
     */
    @Test
    public void statusServicePresentTest() throws Exception {

        mockMvc.perform( get( "/api/status" ) ).andExpect( status().isOk() );
    }

}
