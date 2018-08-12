/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.domain;

/**
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
public enum FuelType {

    F95( "F95" ), F98( "F98" ), D( "D" );

    private String key;

    private FuelType( String key ) {

        this.key = key;
    }

    public String getValue() {

        return this.key;
    }
}
