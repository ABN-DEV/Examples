/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.rest;

import static app.rest.RegisterJsonParserTests.DATE_1990_11_30;
import static app.rest.RegisterJsonParserTests.DATE_1990_12_30;
import static app.rest.RegisterJsonParserTests.DATE_1991_01_30;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_1;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_2;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_3;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_95;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_98;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_D;
import static app.rest.RegisterJsonParserTests.PRICE_1_01;
import static app.rest.RegisterJsonParserTests.PRICE_2_02;
import static app.rest.RegisterJsonParserTests.PRICE_3_03;
import static app.rest.RegisterJsonParserTests.VOLUME_50_01;
import static app.rest.RegisterJsonParserTests.VOLUME_60_02;
import static app.rest.RegisterJsonParserTests.VOLUME_70_03;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
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
@Rollback
public class RegisterControllerTests {

    public static String JSON_MOCKED_REQUEST_1_OBJECT = "{\"fuelType\":\"" + FUEL_TYPE_95.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_1_01
        + "\", "
        + "\"volume\":\""
        + VOLUME_50_01
        + "\", "
        + "\"date\":\""
        + DATE_1990_11_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_1
        + "\" "
        + " }";

    public static String JSON_MOCKED_REQUEST_1_SAME_OBJECT = "{\"fuelType\":\"" + FUEL_TYPE_95.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_2_02
        + "\", "
        + "\"volume\":\""
        + VOLUME_50_01
        + "\", "
        + "\"date\":\""
        + DATE_1990_11_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_2
        + "\" "
        + " }";

    public static final String JSON_MOCKED_REQUEST_ARRAY_FUEL_CONSUMPTIONS = "[" // 

        // 1st object 
        + "{\"fuelType\":\""
        + FUEL_TYPE_98.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_2_02
        + "\", "
        + "\"volume\":\""
        + VOLUME_60_02
        + "\", "
        + "\"date\":\""
        + DATE_1990_12_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_2
        + "\" "
        + " }, "

        // 2nd object 
        + "{\"fuelType\":\""
        + FUEL_TYPE_D.getValue()
        + "\", "
        + "\"price\":\""
        + PRICE_3_03
        + "\", "
        + "\"volume\":\""
        + VOLUME_70_03
        + "\", "
        + "\"date\":\""
        + DATE_1991_01_30.format( DateTimeFormatter.ofPattern( "MM.dd.yyyy" ) )
        + "\", "
        + "\"driverId\":\""
        + DRIVER_ID_3
        + "\" "
        + " } "
        + "]";

    private static final Logger LOG = LoggerFactory.getLogger( RegisterControllerTests.class );

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
    public void test_registerController_post_1_SAME_object_failed() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( JSON_MOCKED_REQUEST_1_SAME_OBJECT );
        ;
        ResultActions mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is2xxSuccessful() );

        MockHttpServletResponse response = mvcResult.andReturn()
            .getResponse();

        LOG.info( "MVC result: status={}, content={} ", response.getStatus(), response.getContentAsString() );

        requestBuilder = MockMvcRequestBuilders.post( "/register" )
            .contentType( MediaType.APPLICATION_JSON_UTF8_VALUE )
            .content( JSON_MOCKED_REQUEST_1_SAME_OBJECT );
        ;
        mvcResult = mockMvc.perform( requestBuilder )
            .andExpect( status().is5xxServerError() );

        response = mvcResult.andReturn()
            .getResponse();

        LOG.info( "MVC result: status={}, content={} ", response.getStatus(), response.getContentAsString() );

    }

    @Test
    @Rollback
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
