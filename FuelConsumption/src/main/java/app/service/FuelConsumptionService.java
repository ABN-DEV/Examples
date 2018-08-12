/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.FuelConsumption;
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
}
