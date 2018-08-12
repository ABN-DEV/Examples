/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.domain.FuelConsumption;
import app.json.TotalSpentAmount;

/**
 * {@link FuelConsumption} repository interface.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@Repository
public interface FuelConsumptionRepository extends CrudRepository<FuelConsumption, Long> {

    /**
     * Found all FuelConsumption.
     * 
     * @return
     */
    @Query( value = " SELECT fc FROM FuelConsumption fc ORDER BY date asc " )
    Collection<FuelConsumption> findTotalSpentAmountPerMonth();

    @Query( value = " SELECT fc FROM FuelConsumption fc WHERE fc.driver = ?1 ORDER BY date asc " )
    Collection<FuelConsumption> findTotalSpentAmountPerMonthByDriver( Integer driverId );

}
