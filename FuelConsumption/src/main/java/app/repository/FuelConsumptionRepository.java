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
import app.json.FuelConsumptionStatistic;

/**
 * {@link FuelConsumption} repository interface.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@Repository
public interface FuelConsumptionRepository extends CrudRepository<FuelConsumption, Long> {

//    @Query( value = " SELECT fc FROM FuelConsumption fc WHERE fc.driverId = ?1 ORDER BY fc.date asc " )
    Collection<FuelConsumption> findOneByDriverId( Integer driverId );

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

    /**
     * FuelConsumption Statistics w/o driver
     * 
     * @return
     */
    @Query( value = " SELECT new app.json.FuelConsumptionStatistic( "
        + "year(fc.date) as year, month(fc.date) as month, fc.fuelType, "
        + "sum(fc.volume) as volume, avg(fc.price) as price, sum(fc.price*fc.volume) as total_price) "
        + " FROM FuelConsumption fc "
        + " GROUP BY year(fc.date), month(fc.date), fc.fuelType "
        + " ORDER BY year, month" )
    Collection<FuelConsumptionStatistic> findStatistics();

    /**
     * FuelConsumption Statistics by driver.
     * 
     * @param driver
     * @return
     */
    @Query( value = " SELECT new app.json.FuelConsumptionStatistic( "
        + "year(fc.date) as year, month(fc.date) as month, fc.fuelType, "
        + "sum(fc.volume) as volume, avg(fc.price) as price, sum(fc.price*fc.volume) as total_price) "
        + " FROM FuelConsumption fc "
        + " where fc.driverId = ?1 "
        + " GROUP BY year(fc.date), month(fc.date), fc.fuelType "
        + " ORDER BY year, month" )
    Collection<FuelConsumptionStatistic> findStatisticsByDriver( Integer driver );

}
