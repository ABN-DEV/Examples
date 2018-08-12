/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import app.domain.FuelConsumption;
import app.domain.FuelType;

/**
 * Test for {@link RegisterJsonParser}.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@SuppressWarnings( "javadoc" )
@RunWith( SpringJUnit4ClassRunner.class )
public class RegisterJsonParserTests {

    public static final int DRIVER_ID_1 = 1;

    public static final LocalDate DATE_2018_11_30 = LocalDate.of( 2018, 11, 30 );

    public static final float VOLUME_43_21 = 43.21f;

    public static final float PRICE_11_23 = 11.23f;

    public static final FuelType FUEL_TYPE_95 = FuelType.F95;

    private String jsonBodyObject;

    private List<FuelConsumption> expectedSingleObject;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        jsonBodyObject = ResgisterControllerTests.JSON_MOCKED_REQUEST_1_OBJECT;

        expectedSingleObject = new ArrayList<FuelConsumption>();
        expectedSingleObject.add(
            new FuelConsumption( FUEL_TYPE_95, PRICE_11_23, VOLUME_43_21, DATE_2018_11_30, DRIVER_ID_1 ) );
    }

    @Test
    public void test_parseSingleObject() {

        RegisterJsonParser parser = new RegisterJsonParser( jsonBodyObject );

        List<FuelConsumption> result = parser.parse();

        assertEquals( "Result of parse is not equal.", expectedSingleObject, result );
    }

//    fail( "Not yet implemented" );

}
