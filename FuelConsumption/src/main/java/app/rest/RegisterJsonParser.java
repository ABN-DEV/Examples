/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import app.domain.FuelConsumption;

/**
 * Parser of JSON body for {@link RegisterController}.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
public class RegisterJsonParser {

    private static final Logger LOG = LoggerFactory.getLogger( RegisterJsonParser.class );

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
    public List<FuelConsumption> parse() throws HttpMessageNotReadableException {

        List<FuelConsumption> fuelConsumptions = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
        mapper.registerModule( new JavaTimeModule() );

        if ( jsonBody.trim()
            .startsWith( "{" ) ) {

            // this JSON is single Object
            JSONObject jsonObject = new JSONObject( jsonBody );

            if ( jsonObject instanceof JSONObject ) {

                try {
                    FuelConsumption fuelConsumption = mapper.readValue( jsonBody, FuelConsumption.class );
                    fuelConsumptions.add( fuelConsumption );

                } catch (Exception e) {
                    final String msg = "Json body does not FuelConsumption single object structure.";
                    LOG.error( "{} ", msg, e );
                    throw new HttpMessageNotReadableException( msg, e );
                }
            }

        } else if ( jsonBody.trim()
            .startsWith( "[" ) ) {

            // this JSON is Array of Objects
            JSONArray jsonArray = new JSONArray( jsonBody );

            if ( jsonArray instanceof JSONArray ) {

                try {
                    fuelConsumptions =
                        mapper.readValue( jsonBody, new TypeReference<List<FuelConsumption>>() {
                        } );

                } catch (Exception e) {
                    final String msg = "Json body does not Array of FuelConsumption structure.";
                    LOG.error( "{} ", msg, e );
                    throw new HttpMessageNotReadableException( msg, e );
                }
            }

        }

        return fuelConsumptions;
    }

}
