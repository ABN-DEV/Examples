/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.rest;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.domain.FuelConsumption;

/**
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@RestController
public class RegisterController {

    /**
     * @param fuelConsumption
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> registerRecords( @Valid @RequestBody FuelConsumption fuelConsumption ) {

        return ResponseEntity.accepted()
            .build();
    }
}
