/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.servies;

import static app.rest.RegisterJsonParserTests.*;
import static app.rest.RegisterJsonParserTests.DATE_1990_12_30;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_1;
import static app.rest.RegisterJsonParserTests.DRIVER_ID_2;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_95;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_98;
import static app.rest.RegisterJsonParserTests.FUEL_TYPE_D;
import static app.rest.RegisterJsonParserTests.PRICE_1_01;
import static app.rest.RegisterJsonParserTests.PRICE_2_02;
import static app.rest.RegisterJsonParserTests.PRICE_3_03;
import static app.rest.RegisterJsonParserTests.VOLUME_50_01;
import static app.rest.RegisterJsonParserTests.VOLUME_60_02;
import static app.rest.RegisterJsonParserTests.VOLUME_70_03;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.Assert.assertEquals;

import app.domain.FuelConsumption;
import app.json.TotalSpentAmount;
import app.repository.FuelConsumptionRepository;
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
public class FuelConsumptionServiceListTests {

    private static final Logger LOG = LoggerFactory.getLogger( FuelConsumptionServiceListTests.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;

    @Before
    public void before() {

        fuelConsumptionRepository.deleteAll();
    };

    @Test
    public void test_retrieve_list() {

        Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_95, PRICE_2_02, VOLUME_60_02, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_98, PRICE_1_01, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_98, PRICE_3_03, VOLUME_70_03, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_2_02, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_3 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_70_03, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        LOG.info( "# 222: {}", fuelConsumptionRepository.findAll() );
        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        int year = DATE_1990_11_30.getYear();
        int month = DATE_1990_11_30.getMonthValue();

        Collection<FuelConsumption> total = fuelConsumptionService.findByMonth( year, month, null );
        LOG.debug( "Retrieved Total : {}", total );

        assertEquals( "Inserted records ", 6, total.size() );

    }

}
