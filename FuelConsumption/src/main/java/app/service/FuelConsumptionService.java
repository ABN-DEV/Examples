/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.FuelConsumption;
import app.json.TotalSpentAmount;
import app.repository.FuelConsumptionRepository;
import app.rest.exception.DriverNotFoundException;

/**
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@Service
public class FuelConsumptionService {

    @Autowired
    private FuelConsumptionRepository fuelConsumptionRepository;

    /**
     * @param fuelConsumptions
     *            {@link Collection} of {@link FuelConsumption}.
     * @return
     */
    public Collection<FuelConsumption> saveAll( Collection<FuelConsumption> fuelConsumptions ) {

        return (Collection<FuelConsumption>) fuelConsumptionRepository.saveAll( fuelConsumptions );
    }

    /**
     * Find Total Spent Amount per month.
     * 
     * @param driverId
     *            - nullable. If it null all drivers included.
     * @return a {@link Collection} of {@link TotalSpentAmount}.
     */
    public ConcurrentMap<String, TotalSpentAmount> findTotalSpentAmount( Integer driverId ) {

        final ConcurrentMap<String, TotalSpentAmount> totalAmount =
            new ConcurrentHashMap<String, TotalSpentAmount>();

        Collection<FuelConsumption> totalFuelConsumption = null;

        if ( driverId == null ) {
            totalFuelConsumption = fuelConsumptionRepository.findTotalSpentAmountPerMonth();

        } else {
            checkDriverId( driverId );
            totalFuelConsumption = fuelConsumptionRepository.findTotalSpentAmountPerMonthByDriver( driverId );
        }

        totalFuelConsumption.stream()
            .forEach( fc -> {

                String key = fc.getDate()
                    .format( DateTimeFormatter.ofPattern( "yyyy-MM" ) );

                TotalSpentAmount currentTotalAmount = null;
                BigDecimal amount = BigDecimal.ZERO;

                if ( totalAmount.containsKey( key ) ) {
                    currentTotalAmount = totalAmount.get( key );
                    amount = currentTotalAmount.getAmount();
                }
                totalAmount.put( key,
                    new TotalSpentAmount( key,
                        amount.add( fc.getPrice()
                            .multiply( fc.getVolume() ) ) ) );
            } );

        return totalAmount;
    }

    /**
     * @param year
     * @param month
     *            is the digit from 1 to 12.
     * @param driverId
     *            is a nullable.
     * @return {@link Collection} of {@link FuelConsumption}s.
     */
    public Collection<FuelConsumption> findByMonth( int year,
            int month,
            Integer driverId ) {

        Collection<FuelConsumption> fuelConsumptions = null;
        if ( driverId == null ) {
            fuelConsumptions = fuelConsumptionRepository.findRecordsForMonth( year, month );

        } else {
            checkDriverId( driverId );
            fuelConsumptions = fuelConsumptionRepository.findRecordsForMonthByDriver( year, month, driverId );
        }
        return fuelConsumptions;
    }

    /**
     * Statistics for each month, list fuel consumption records grouped by fuel type (each row should contain:
     * fuel type, volume, average price, total price)
     * 
     * @param driverId
     *            is a drive ID
     * @return {@link Collection} of {@link FuelConsumption}s.
     */
    public Collection<FuelConsumption> findStatistics( Integer driverId ) {

        Collection<FuelConsumption> fuelConsumptions = null;
        if ( driverId == null ) {
            fuelConsumptions = fuelConsumptionRepository.findStatistics();

        } else {
            checkDriverId( driverId );
            fuelConsumptions = fuelConsumptionRepository.findStatisticsByDriver( driverId );
        }
        return fuelConsumptions;
    }

    /**
     * It checks driverId. At the moment based on FuelConsumption records.
     * 
     * @param driverId
     */
    public void checkDriverId( Integer driverId ) {

        final Collection<FuelConsumption> findOneByDriverId =
            fuelConsumptionRepository.findOneByDriverId( driverId );
        if ( findOneByDriverId == null || findOneByDriverId.size() == 0 ) {
            throw new DriverNotFoundException( "Driver [id:" + driverId + "] not found." );
        }
    }
}
