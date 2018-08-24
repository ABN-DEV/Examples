/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.domain.FuelConsumption;
import app.json.FuelConsumptionStatistic;
import app.json.TotalSpentAmount;
import app.service.FuelConsumptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@RestController
public class RetrieveController {

    public static final String MAIN_URL = "/retrieve";

    public static final Logger LOG = LoggerFactory.getLogger( RetrieveController.class );

    public static final String TOTAL_URL = "/total";

    public static final String STATISTICS_SUB_URL = "/statistics";

    public static final String LIST_SUB_URL = "/list/{year}-{month}";

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    /**
     * Retrieve total spent amount of money grouped by month.
     * 
     * @return Total spent amount gouped by month.
     */
    @ApiOperation( value = "Retrieve total spent amount of money grouped by month." )
    @GetMapping( path = {MAIN_URL + TOTAL_URL}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    public ResponseEntity<Object>
            retrieveTotal( @RequestParam( required = false, name = "driver" ) Integer driverId ) {

        ConcurrentMap<String, TotalSpentAmount> totalSpentAmount =
            fuelConsumptionService.findTotalSpentAmount( driverId );

        return ResponseEntity.ok()
            .body( totalSpentAmount );
    }

    /**
     * @param year
     *            is a year in format 'yyyy'
     * @param month
     *            is a month digit from 1 to 12.
     * @param driverId
     * @return fuel consumption records for specified month.
     */
    @ApiOperation(
            notes = "(each row should contain: fuel type, volume, date, price, total price, driver ID) .",
            value = "A list fuel consumption records for specified month." )
    @GetMapping( path = {MAIN_URL + LIST_SUB_URL}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    public ResponseEntity<Object> retrieveFuelConsumptionForMonth( @PathVariable( name = "year" ) int year,
            @PathVariable( name = "month" ) int month,
            @RequestParam( required = false, name = "driver" ) Integer driverId ) {

        Collection<FuelConsumption> allFuelConsumptionByMonth =
            fuelConsumptionService.findByMonth( year, month, driverId );

        return ResponseEntity.ok()
            .body( allFuelConsumptionByMonth );
    }

    /**
     * 
     * @param year
     * @param month
     * @param driverId
     * @return
     */
    @ApiOperation( notes = " (each row should contain: fuel type, volume, average price, total price).",
            value = "The statistics for each month, list fuel consumption records grouped by fuel type." )
    @GetMapping( path = {MAIN_URL + STATISTICS_SUB_URL}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    public ResponseEntity<Object> retrieveStatisticsFuelConsumption(
            @RequestParam( required = false, name = "driver" ) Integer driverId ) {

        Collection<FuelConsumptionStatistic> allFuelConsumptionByMonth =
            fuelConsumptionService.findStatistics( driverId );

        return ResponseEntity.ok()
            .body( allFuelConsumptionByMonth );
    }

}
