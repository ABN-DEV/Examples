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
    @Query( value = " SELECT fc FROM FuelConsumption fc ORDER BY fc.date asc " )
    Collection<FuelConsumption> findTotalSpentAmountPerMonth();

    @Query( value = " SELECT fc FROM FuelConsumption fc WHERE fc.driverId = ?1 ORDER BY fc.date asc " )
    Collection<FuelConsumption> findTotalSpentAmountPerMonthByDriver( Integer driverId );

    /**
     * @param year
     *            in format yyyy
     * @param month
     *            is the digit from 1 to 12
     * @return {@link Collection} of {@link FuelConsumption}s.
     */
    @Query( value = " SELECT fc FROM FuelConsumption fc "
        + "where year(fc.date) = ?1 and month(fc.date) = ?2 ORDER BY fc.date asc " )
    Collection<FuelConsumption> findRecordsForMonth( int year,
            int month );

    /**
     * @param year
     *            in format yyyy
     * @param month
     *            is the digit from 1 to 12
     * @param driverId
     *            is the driver ID.
     * @return {@link Collection} of {@link FuelConsumption}s.
     */
    @Query( value = " SELECT fc FROM FuelConsumption fc "
        + "where year(fc.date) = ?1 and month(fc.date) = ?2 and fc.driverId = ?3 "
        + "ORDER BY fc.date asc " )
    Collection<FuelConsumption> findRecordsForMonthByDriver( int year,
            int month,
            Integer driverId );

    @Query( value = " SELECT year(fc.date) as y, month(fc.date) as m, fc.fuelType, sum(fc.volume) as sumv, "
        + "avg(fc.price) as avgprice, sum(fc.price) as sumprice "
        + " FROM FuelConsumption fc "
        + " GROUP BY year(fc.date), month(fc.date), fc.fuelType "
        + " ORDER BY y, m " )
    Collection<FuelConsumption> findStatistics();

    /**
     * SELECT extract( year from fc.date) as y, extract( month from fc.date) as m, fc.fuel_type, SUM(
     * fc.volume), avg( fc.price), sum(fc.price)
     * 
     * FROM FUEL_CONSUMPTION fc GROUP BY extract( year from fc.date), extract( month from fc.date) ,
     * fc.fuel_type
     * 
     * @param driver
     * @return
     */
    @Query( value = " SELECT year(fc.date) as y, month(fc.date) as m, fc.fuelType, sum(fc.volume), avg(fc.price), sum(fc.price) "
        + " FROM FuelConsumption fc "
        + " where fc.driverId = ?1 "
        + " GROUP BY year(fc.date), month(fc.date), fc.fuelType "
        + " ORDER BY y, m" )
    Collection<FuelConsumption> findStatisticsByDriver( Integer driver );

}
