/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.servies;

import static app.rest.RegisterJsonParserTests.DATE_1990_11_30;
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
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
@Rollback
public class FuelConsumptionServiceTests3 {

    private static final Logger LOG = LoggerFactory.getLogger( FuelConsumptionServiceTests3.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * It should return total spent amount of money grouped by month
     */
    @Test
    public void test_retrieve_total() {

        Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_95, PRICE_2_02, VOLUME_60_02, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_98, PRICE_1_01, VOLUME_50_01, DATE_1990_12_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_70_03, DATE_1990_12_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

//        thrown.expect( DuplicateRecordsException.class );

        // we have there above 2 months: 1990/11 & 1990/12
        BigDecimal totalAmount199011 = PRICE_1_01.multiply( VOLUME_50_01 )
            .add( PRICE_2_02.multiply( VOLUME_60_02 ) );
        LOG.debug( "Total amount for 1990/11 = {}", totalAmount199011.toString() );

        BigDecimal totalAmount199012 = PRICE_1_01.multiply( VOLUME_50_01 )
            .add( PRICE_3_03.multiply( VOLUME_70_03 ) );
        LOG.debug( "Total amount for 1990/12 = {}", totalAmount199012.toString() );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

        ConcurrentMap<String, TotalSpentAmount> total = fuelConsumptionService.findTotalSpentAmount( null );
        LOG.debug( "Retrieved Total : {}", total );

        // key is yyyy-MM 
        final DateTimeFormatter keyPattern = DateTimeFormatter.ofPattern( "yyyy-MM" );
        
        String key = DATE_1990_11_30.format( keyPattern );
        LOG.debug( "key : {}", key );

        assertEquals( "Total amount for 1990/11",
            totalAmount199011,
            total.get( key )
                .getAmount() );

        key = DATE_1990_12_30.format( keyPattern );
        assertEquals( "Total amount for 1990/12",
            totalAmount199012,
            total.get( key )
                .getAmount() );

    }

}
