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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import app.domain.FuelConsumption;
import app.repository.FuelConsumptionRepository;
import app.rest.exception.DuplicateRecordsException;
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
public class FuelConsumptionServiceTests2 {

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_saveAll_contraint() {

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1991_01_30, DRIVER_ID_1 );

        Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();
        fuelConsumptions.add( fc );

        thrown.expect( DuplicateRecordsException.class );

        // add same record 
        fc = new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1991_01_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

    }

}
