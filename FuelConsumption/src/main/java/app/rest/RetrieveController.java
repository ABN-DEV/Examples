/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest;

import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.json.TotalSpentAmount;
import app.service.FuelConsumptionService;

/**
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
@RestController
public class RetrieveController {

    public static final String MAIN_URL = "/retrieve";

    private static final Logger LOG = LoggerFactory.getLogger( RetrieveController.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    /**
     * Retrieve total spent amount of money grouped by month.
     * 
     * @return
     */
    @GetMapping( produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
    public ResponseEntity<Object> retrieveTotal() {

        ResponseEntity<Object> result = null;

        ConcurrentMap<String, TotalSpentAmount> totalSpentAmount =
            fuelConsumptionService.findTotalSpentAmount( null );

        return result.ok()
            .body( totalSpentAmount );
    }

}
