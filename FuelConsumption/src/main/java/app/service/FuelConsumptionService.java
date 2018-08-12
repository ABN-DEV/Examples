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

        final Collection<FuelConsumption> totalFuelConsumption =
            fuelConsumptionRepository.findTotalSpentAmountPerMonth();

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
}
