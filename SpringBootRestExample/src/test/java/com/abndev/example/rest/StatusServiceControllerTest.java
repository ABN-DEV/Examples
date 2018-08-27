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

import com.abndev.example.TestConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @since 2018.05.30
 * @author annik
 */
//@RunWith( SpringJUnit4ClassRunner.class )
@RunWith( SpringRunner.class )
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration( classes = TestConfig.class )
public class StatusServiceControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     * Test Initialization.
     */
    @Before
    public void init() {

        mockMvc = MockMvcBuilders.webAppContextSetup( wac )
            .build();
    }

    /**
     * @throws Exception
     */
    @Test
    public void statusServicePresentTest() throws Exception {

        mockMvc.perform( get( "/api/status" ) )
            .andExpect( status().isOk() );
    }

}
