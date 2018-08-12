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

    public static final int DRIVER_ID_2 = 2;

    public static final int DRIVER_ID_3 = 3;

    public static final LocalDate DATE_2018_11_30 = LocalDate.of( 2018, 11, 30 );

    public static final LocalDate DATE_2018_12_30 = LocalDate.of( 2018, 12, 30 );

    public static final LocalDate DATE_2019_01_30 = LocalDate.of( 2019, 01, 30 );

    public static final float VOLUME_43_21 = 43.21f;

    public static final float VOLUME_43_22 = 43.22f;

    public static final float VOLUME_43_23 = 43.23f;

    public static final float PRICE_11_23 = 11.23f;

    public static final float PRICE_11_24 = 11.24f;

    public static final float PRICE_11_25 = 11.25f;

    public static final FuelType FUEL_TYPE_95 = FuelType.F95;

    public static final FuelType FUEL_TYPE_98 = FuelType.F98;

    public static final FuelType FUEL_TYPE_D = FuelType.D;

    private String jsonBodyObject;

    private String jsonBodyArrayFuelConsumptions;

    private List<FuelConsumption> expectedSingleObject;

    private List<FuelConsumption> expectedArrayFuelConsumptions;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        jsonBodyObject = ResgisterControllerTests.JSON_MOCKED_REQUEST_1_OBJECT;
        jsonBodyArrayFuelConsumptions = ResgisterControllerTests.JSON_MOCKED_REQUEST_ARRAY_FUEL_CONSUMPTIONS;

        expectedSingleObject = new ArrayList<FuelConsumption>();
        expectedSingleObject.add(
            new FuelConsumption( FUEL_TYPE_95, PRICE_11_23, VOLUME_43_21, DATE_2018_11_30, DRIVER_ID_1 ) );

        expectedArrayFuelConsumptions = new ArrayList<>();
        expectedArrayFuelConsumptions.add(
            new FuelConsumption( FUEL_TYPE_95, PRICE_11_23, VOLUME_43_21, DATE_2018_11_30, DRIVER_ID_1 ) );
        expectedArrayFuelConsumptions.add(
            new FuelConsumption( FUEL_TYPE_98, PRICE_11_24, VOLUME_43_22, DATE_2018_12_30, DRIVER_ID_2 ) );
        expectedArrayFuelConsumptions.add(
            new FuelConsumption( FUEL_TYPE_D, PRICE_11_25, VOLUME_43_23, DATE_2019_01_30, DRIVER_ID_3 ) );

    }

    @Test
    public void test_parseSingle_FuelConsumption() {

        RegisterJsonParser parser = new RegisterJsonParser( jsonBodyObject );

        List<FuelConsumption> result = parser.parse();

        assertEquals( "Result of parse is not equal.", expectedSingleObject, result );
    }

    @Test
    public void test_parse_Array_of_FuelConsumption() {

        RegisterJsonParser parser = new RegisterJsonParser( jsonBodyArrayFuelConsumptions );

        List<FuelConsumption> result = parser.parse();

        assertEquals( "Result of parse is not equal.", expectedArrayFuelConsumptions, result );
    }

}
