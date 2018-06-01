/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.rest;

import com.abndev.example.TestConfig;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @since 2018.06.01
 * @author annik
 */
@RunWith( SpringJUnit4ClassRunner.class )
//@RunWith( SpringRunner.class )
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration( classes = TestConfig.class )
public abstract class AbstractTests {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    /**
     * Test Initialization.
     */
    @Before
    public void init() {

        mockMvc = MockMvcBuilders.webAppContextSetup( wac )
            .build();
    }

}
