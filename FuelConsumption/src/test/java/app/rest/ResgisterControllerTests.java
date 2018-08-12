/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.rest;

import static app.rest.RegisterJsonParserTests.DATE_2018_11_30;
import static app.rest.RegisterJsonParserTests.DATE_2018_12_30;
import static app.rest.RegisterJsonParserTests.DATE_2019_01_30;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_1;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_2;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_3;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_95;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_98;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_D;
import static app.rest.RegisterJsonParserTests.PRICE_11_23;
import static app.rest.RegisterJsonParserTests.PRICE_11_24;
import static app.rest.RegisterJsonParserTests.PRICE_11_25;
import static app.rest.RegisterJsonParserTests.VOLUME_43_21;
import static app.rest.RegisterJsonParserTests.VOLUME_43_22;
import static app.rest.RegisterJsonParserTests.VOLUME_43_23;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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
@WebAppConfiguration
@SpringBootTest
public class ResgisterControllerTests {

    public static String JSON_MOCKED_REQUEST_1_OBJECT = "{\"fuelType\":\"" + FUEL_TYPE_95.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_11_23
        + "\", "
        + "\"volume\":\""
        + VOLUME_43_21
        + "\", "
        + "\"date\":\""
        + DATE_2018_11_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_1
        + "\" "
        + " }";

    public static final String JSON_MOCKED_REQUEST_ARRAY_FUEL_CONSUMPTIONS = "[" // 
        + "{\"fuelType\":\""
        + FUEL_TYPE_95.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_11_23
        + "\", "
        + "\"volume\":\""
        + VOLUME_43_21
        + "\", "
        + "\"date\":\""
        + DATE_2018_11_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_1
        + "\" "
        + " }, "

        // second object 
        + "{\"fuelType\":\""
        + FUEL_TYPE_98.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_11_24
        + "\", "
        + "\"volume\":\""
        + VOLUME_43_22
        + "\", "
        + "\"date\":\""
        + DATE_2018_12_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_2
        + "\" "
        + " }, "

        // firf object 
        + "{\"fuelType\":\""
        + FUEL_TYPE_D.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_11_25
        + "\", "
        + "\"volume\":\""
        + VOLUME_43_23
        + "\", "
        + "\"date\":\""
        + DATE_2019_01_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_3
        + "\" "
        + " } "
        + "]";

    private static final Logger LOG = LoggerFactory.getLogger( ResgisterControllerTests.class );

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
    public void test_registerController_post_emtpy_4xx() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( new byte[] {} );
        ;
        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is4xxClientError() );

    }

    @Test
    public void test_registerController_post_1_object() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( JSON_MOCKED_REQUEST_1_OBJECT );
        ;
        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is2xxSuccessful() );

        final MockHttpServletResponse response = mvcResult.andReturn()
            .getResponse();

        LOG.info( "MVC result: status={}, content={} ", response.getStatus(), response.getContentAsString() );

    }

    @Test
    public void test_registerController_post_ArrayOfObject() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( JSON_MOCKED_REQUEST_ARRAY_FUEL_CONSUMPTIONS );
        ;
        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is2xxSuccessful() );

        final MockHttpServletResponse response = mvcResult.andReturn()
            .getResponse();

        LOG.info( "MVC result: status={}, content={} ", response.getStatus(), response.getContentAsString() );

    }

}
