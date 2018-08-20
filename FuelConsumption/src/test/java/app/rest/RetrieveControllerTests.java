/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import app.rest.exception.DriverNotFoundException;

/**
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@SuppressWarnings( "javadoc" )

@RunWith( SpringRunner.class )
@WebAppConfiguration
@SpringBootTest

public class RetrieveControllerTests {

    private static final Logger LOG = LoggerFactory.getLogger( RetrieveControllerTests.class );

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

    }

    @Test
    public void test_retrieve_total_get() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.TOTAL_URL );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isOk() );

    }

    @Test
    public void test_get_retrieve_total_Driver() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.TOTAL_URL )
                .param( "driver", "2" );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isOk() );

    }

    @Test
    public void test_get_retrieve_total_Driver_NotFound() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.TOTAL_URL )
                .param( "driver", "11" );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isNotFound() );

    }

    @Test
    public void test_retrieve_statistics_get() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.STATISTICS_SUB_URL );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isOk() );

    }

    @Test
    public void test_get_retrieve_statistics_Driver() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.STATISTICS_SUB_URL )
                .param( "driver", "2" );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isOk() );

    }

    @Test
    public void test_get_retrieve_statistics_Driver_NotFound() throws Exception {

        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get( RetrieveController.MAIN_URL + RetrieveController.STATISTICS_SUB_URL )
                .param( "driver", "11" );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isNotFound() );

    }

    @Test
    public void test_get_retrieve_list_get() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get( RetrieveController.MAIN_URL + RetrieveController.LIST_SUB_URL, 2018, 12 );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isOk() );

    }

    @Test
    public void test_get_retrieve_list_Driver_NotFound() throws Throwable {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get( RetrieveController.MAIN_URL + RetrieveController.LIST_SUB_URL, 2018, 12 )
            .param( "driver", "11" );

        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().isNotFound() );

    }

}
