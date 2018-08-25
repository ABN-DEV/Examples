/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.servies;

import static app.rest.RegisterJsonParserTests.DATE_1991_01_30;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_1;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_95;
import static app.rest.RegisterJsonParserTests.PRICE_1_01;
import static app.rest.RegisterJsonParserTests.VOLUME_50_01;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
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
@Rollback
@Transactional
public class FuelConsumptionServiceTests {

    private static final Logger LOG = LoggerFactory.getLogger( FuelConsumptionServiceTests.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Test
    public void test_saveAll_FuelConsumptions() {

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1991_01_30, DRIVER_ID_1 );

        assertNull( fc.getGid() );

        final Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        assertNotNull( saved );

        assertNotNull( ( (FuelConsumption) saved.iterator()
            .next() ).getGid() );
    }

    @Test
    public void test_saveAll_1_fuelConsumption() {

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1991_01_30, DRIVER_ID_1 );

        final Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        assertEquals( "Must be one object.", Integer.valueOf( 1 ), Integer.valueOf( saved.size() ) );

        final Long gid = saved.iterator()
            .next()
            .getGid();

        assertNotNull( "GID must defined and not null.", gid );

        LOG.debug( "new gid = {}", gid );
    }

}
