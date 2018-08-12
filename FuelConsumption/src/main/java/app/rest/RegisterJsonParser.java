/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest;

import java.util.List;

import app.domain.FuelConsumption;

/**
 * Parser of JSON body for {@link RegisterController}.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
public class RegisterJsonParser {

    private String jsonBody;

    /**
     * Contructor.
     * 
     * @param jsonBody
     */
    public RegisterJsonParser( String jsonBody ) {

        this.jsonBody = jsonBody;

    }

    /**
     * Parser of JSON.
     * 
     * @return {@link List} of {@link FuelConsumption}s.
     */
    public List<FuelConsumption> parse() {

        return null;
    }

}
