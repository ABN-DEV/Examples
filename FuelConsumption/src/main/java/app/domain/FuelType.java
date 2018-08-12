/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.domain;

import java.util.Objects;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
public enum FuelType {

    @JsonProperty( "95" )
    F95("95" ),

    @JsonProperty( "98" )
    F98("98" ),

    @JsonProperty( "D" )
    D("D" );

    private String key;

    private FuelType( String key ) {

        this.key = key;
    }

    public String getValue() {

        return this.key;
    }

    /**
     * Converter for {@link FuelType}.
     * 
     * @since 2018.08.12
     * @author Andre.B.Nikitin
     */
    @javax.persistence.Converter( autoApply = false )
    public static class Converter implements AttributeConverter<FuelType, String> {

        @Override
        public String convertToDatabaseColumn( FuelType fuelType ) {

            return fuelType != null ? fuelType.key : null;
        }

        @Override
        public FuelType convertToEntityAttribute( String s ) {

            if ( s == null ) {
                return null;
            }

            for (FuelType fuelType : FuelType.values()) {
                if ( Objects.equals( fuelType.key, s ) ) {
                    return fuelType;
                }
            }

            return null;
        }
    }

}
