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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import app.domain.FuelConsumption;
import app.domain.FuelType;
import app.json.FuelConsumptionStatistic;
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
public class FuelConsumptionServiceStatisticsTests {

    private static final Logger LOG = LoggerFactory.getLogger( FuelConsumptionServiceStatisticsTests.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;

    @Before
    public void test_retrieve_Statistics() {

        fuelConsumptionRepository.deleteAll();

        LOG.info( "# 333: {}", fuelConsumptionRepository.findAll() );

        Collection<FuelConsumption> fuelConsumptions = new ArrayList<FuelConsumption>();

        FuelConsumption fc =
            new FuelConsumption( FUEL_TYPE_95, PRICE_1_01, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_95, PRICE_2_02, VOLUME_60_02, DATE_1990_11_30, DRIVER_ID_1 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_95, PRICE_3_03, VOLUME_70_03, DATE_1990_11_30, DRIVER_ID_3 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_98, PRICE_1_01, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_98, PRICE_3_03, VOLUME_70_03, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_2_02, VOLUME_50_01, DATE_1990_11_30, DRIVER_ID_3 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_70_03, DATE_1990_11_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_50_01, DATE_1991_01_30, DRIVER_ID_3 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_60_02, DATE_1991_01_30, DRIVER_ID_3 );
        fuelConsumptions.add( fc );

        fc = new FuelConsumption( FUEL_TYPE_D, PRICE_3_03, VOLUME_70_03, DATE_1991_01_30, DRIVER_ID_2 );
        fuelConsumptions.add( fc );

        Collection<FuelConsumption> saved = fuelConsumptionService.saveAll( fuelConsumptions );

    }

    /**
     * It checks All Fuel Consumption F95 statistics.
     * 
     * @param fuelConsumptionStats
     * @param fuelConsumptions
     */
    @Test
    public void checkF95() {

        int year = DATE_1990_11_30.getYear();
        int month = DATE_1990_11_30.getMonthValue();

        Collection<FuelConsumptionStatistic> total = fuelConsumptionService.findStatistics( null );
        LOG.debug( "Retrieved all Statistics : {}", total );

        Optional<FuelConsumptionStatistic> fuelConsumptionStats = total.stream()
            .filter( fcs -> fcs.getYear() == year && fcs.getMonth() == month
                && fcs.getFuelType()
                    .equals( FuelType.F95 ) )
            .findAny();

        // we have there above 1 month: 1990/11 
        BigDecimal totalAmountF95 = PRICE_1_01.multiply( VOLUME_50_01 )
            .add( PRICE_2_02.multiply( VOLUME_60_02 ) )
            .add( PRICE_3_03.multiply( VOLUME_70_03 ) );

        assertEquals( "Total Amount F95 1990/11 ",
            totalAmountF95,
            fuelConsumptionStats.get()
                .getTotalAmount() );

        //
        BigDecimal totalAmountVolume = VOLUME_50_01.add( VOLUME_60_02 )
            .add( VOLUME_70_03 );

        assertEquals( "Total Volume F95 1990/11 ",
            totalAmountVolume,
            fuelConsumptionStats.get()
                .getSumVolume() );

        //
        BigDecimal avgPrice = PRICE_1_01.add( PRICE_2_02 )
            .add( PRICE_3_03 )
            .divide( BigDecimal.valueOf( 3 ) );

        assertTrue( "Average Price F95 1990/11 ",
            Math.abs( avgPrice.doubleValue() - fuelConsumptionStats.get()
                .getAvgPrice() ) < 0.00001 );
    }

    /**
     * It checks Fuel 95 statistics.
     * 
     * @param fuelConsumptionStats
     */
    @Test
    public void checkF95ByDriver1() {

        int year = DATE_1990_11_30.getYear();
        int month = DATE_1990_11_30.getMonthValue();

        Collection<FuelConsumptionStatistic> total = fuelConsumptionService.findStatistics( DRIVER_ID_1 );
        LOG.debug( "Retrieved all Statistics : {}", total );

        Optional<FuelConsumptionStatistic> fuelConsumptionStats = total.stream()
            .filter( fcs -> fcs.getYear() == year && fcs.getMonth() == month
                && fcs.getFuelType()
                    .equals( FuelType.F95 ) )
            .findAny();

        // we have there above 1 month: 1990/11 
        BigDecimal totalAmountF95 = PRICE_1_01.multiply( VOLUME_50_01 )
            .add( PRICE_2_02.multiply( VOLUME_60_02 ) );

        assertEquals( "Total Amount F95 1990/11 ",
            totalAmountF95,
            fuelConsumptionStats.get()
                .getTotalAmount() );

        //
        BigDecimal totalAmountVolume = VOLUME_50_01.add( VOLUME_60_02 );

        assertEquals( "Total Volume F95 1990/11 ",
            totalAmountVolume,
            fuelConsumptionStats.get()
                .getSumVolume() );

        //
        BigDecimal avgPrice = PRICE_1_01.add( PRICE_2_02 )
            .divide( BigDecimal.valueOf( 2 ) );

        assertTrue( "Average Price F95 1990/11 ",
            Math.abs( avgPrice.doubleValue() - fuelConsumptionStats.get()
                .getAvgPrice() ) < 0.00001 );
    }

    @Test
    public void checkF95ByDriver3() {

        int year = DATE_1990_11_30.getYear();
        int month = DATE_1990_11_30.getMonthValue();
        final String yearMonth = year + "/" + month;

        Collection<FuelConsumptionStatistic> total = fuelConsumptionService.findStatistics( DRIVER_ID_3 );
        LOG.debug( "Retrieved all Statistics : {}", total );

        Optional<FuelConsumptionStatistic> fuelConsumptionStats = total.stream()
            .filter( fcs -> fcs.getYear() == year && fcs.getMonth() == month
                && fcs.getFuelType()
                    .equals( FuelType.F95 ) )
            .findAny();

        // we have there above 1 month: 1990/11 
        BigDecimal totalAmountF95 = PRICE_3_03.multiply( VOLUME_70_03 );

        assertEquals( "Total Amount F95 " + yearMonth,
            totalAmountF95,
            fuelConsumptionStats.get()
                .getTotalAmount() );

        //
        BigDecimal totalAmountVolume = VOLUME_70_03;

        assertEquals( "Total Volume F95 " + yearMonth,
            totalAmountVolume,
            fuelConsumptionStats.get()
                .getSumVolume() );

        //
        BigDecimal avgPrice = PRICE_3_03.divide( BigDecimal.valueOf( 1 ) );

        assertTrue( "Average Price F95 " + yearMonth,
            Math.abs( avgPrice.doubleValue() - fuelConsumptionStats.get()
                .getAvgPrice() ) < 0.00001 );
    }

    @Test
    public void checkDieselByDriver2() {

        int year = DATE_1991_01_30.getYear();
        int month = DATE_1991_01_30.getMonthValue();
        final String yearMonth = year + "/" + month;

        Collection<FuelConsumptionStatistic> total = fuelConsumptionService.findStatistics( DRIVER_ID_3 );
        LOG.debug( "Retrieved all Statistics : {}", total );

        Optional<FuelConsumptionStatistic> fuelConsumptionStats = total.stream()
            .filter( fcs -> fcs.getYear() == year && fcs.getMonth() == month
                && fcs.getFuelType()
                    .equals( FuelType.D ) )
            .findAny();

        BigDecimal totalAmountDiesel = PRICE_3_03.multiply( VOLUME_50_01 )
            .add( PRICE_3_03.multiply( VOLUME_60_02 ) );

        assertEquals( "Total Amount Fuel [D] " + yearMonth,
            totalAmountDiesel,
            fuelConsumptionStats.get()
                .getTotalAmount() );

        //
        BigDecimal totalAmountVolume = VOLUME_50_01.add( VOLUME_60_02 );

        assertEquals( "Total Volume Diesele : " + yearMonth,
            totalAmountVolume,
            fuelConsumptionStats.get()
                .getSumVolume() );

        //
        BigDecimal avgPrice = PRICE_3_03.add( PRICE_3_03 )
            .divide( BigDecimal.valueOf( 2 ) );

        assertTrue( "Average Price F95 " + yearMonth,
            Math.abs( avgPrice.doubleValue() - fuelConsumptionStats.get()
                .getAvgPrice() ) < 0.00001 );
    }

}
