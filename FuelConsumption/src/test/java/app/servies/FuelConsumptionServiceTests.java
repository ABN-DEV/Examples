/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.servies;

import static app.rest.RegisterJsonParserTests.DATE_2019_01_30;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_1;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_95;
import static app.rest.RegisterJsonParserTests.PRICE_11_23;
import static app.rest.RegisterJsonParserTests.VOLUME_43_21;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import app.domain.FuelConsumption;
import app.service.FuelConsumptionService;

/**
 * {@link FuelConsumptionService} tests.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@SuppressWarnings( "javadoc" )

@RunWith( SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@SpringBootTest
//@Transactional
//@TransactionConfiguration( defaultRollback = true )
public class FuelConsumptionServiceTests {

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Test
    public void test_saveAll_FuelConsumptions() {

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_11_23, VOLUME_43_21, DATE_2019_01_30, DRIVER_ID_1 );

        assertNull( fc.getGid() );

        final ArrayList<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        assertNotNull( saved );

        assertNotNull( ( (FuelConsumption) saved.iterator()
            .next() ).getGid() );
    }

    @Test( expected = Exception.class )
    public void test_saveAll_contraint() {

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_11_23, VOLUME_43_21, DATE_2019_01_30, DRIVER_ID_1 );

        final ArrayList<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        saved = fuelConsumptionService.saveAll( fuelConsumptions );
    }

}
