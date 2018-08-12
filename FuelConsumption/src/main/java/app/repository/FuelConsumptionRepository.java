/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.domain.FuelConsumption;

/**
 * {@link FuelConsumption} repository interface.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@Repository
public interface FuelConsumptionRepository extends CrudRepository<FuelConsumption, Long> {

}
