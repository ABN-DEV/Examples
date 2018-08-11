/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@RunWith( SpringRunner.class )
@WebMvcTest
public class ResgisterControllerTests {

    private static final Logger LOG = LoggerFactory.getLogger( ResgisterControllerTests.class );

    private String JSON_MOCKED_REQUEST = null;

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks( this );

        mockMvc = MockMvcBuilders.webAppContextSetup( webApplicationContext )
            .alwaysDo( print() )
            .build();

        JSON_MOCKED_REQUEST = "";

    }

    @Test
    public void test_registerController_post() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( JSON_MOCKED_REQUEST );
        ;
        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is5xxServerError() );

    }

//    fail( "Not yet implemented" );
}
