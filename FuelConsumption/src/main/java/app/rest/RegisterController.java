/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.rest;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.domain.FuelConsumption;
import app.repository.FuelConsumptionService;

/**
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@RestController
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger( RegisterController.class );

    @Autowired
    private FuelConsumptionService fuelConsumptionService;

    /**
     * @param jsonBody
     *            - a JSON string of {@link FuelConsumption} Object or Array of {@link FuelConsumption} Object
     * @return the result of registering fuel consumption..
     */
    @PostMapping( value = {"/register"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE} )
    public ResponseEntity<Object> registerRecord( @RequestBody( required = true ) String jsonBody ) {

        LOG.debug( "JSON body: {}", jsonBody );

        if ( StringUtils.isBlank( jsonBody ) || "".equals( jsonBody.trim() ) ) {
            LOG.error( "Body is empty." );
            throw new HttpMessageNotReadableException( "Body is empty." );
        }

        List<FuelConsumption> fuelConsumptions = new RegisterJsonParser( jsonBody ).parse();

        LOG.debug( "Parsed FuelConsumption from JSON: {}", fuelConsumptions );

        List<FuelConsumption> savedFuelConsumptions =
            (List<FuelConsumption>) fuelConsumptionService.saveAll( fuelConsumptions );

//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path( "/{id}" )
//            .buildAndExpand( savedUser.getGid() )
//            .toUri();

        LOG.debug( "Saved Fuel Consumptions: {}", savedFuelConsumptions );

        return ResponseEntity.ok()
            .build();
    }

}
