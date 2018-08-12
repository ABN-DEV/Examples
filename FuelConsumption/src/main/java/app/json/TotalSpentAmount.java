/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-12<br>
 * <br>
 */
package app.json;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * JSON Bean - total spent amount.
 * 
 * @since 2018.08.12
 * @author Andre.B.Nikitin
 */
public class TotalSpentAmount {

    @JsonInclude( JsonInclude.Include.NON_NULL )
    private Integer driverId;

    private String month;

    private BigDecimal amount;

    /**
     * Contructor.
     * 
     * @param driverId
     * @param month
     * @param amount
     */
    public TotalSpentAmount( Integer driverId, String month, BigDecimal amount ) {

        this.driverId = driverId;
        this.month = month;
        this.amount = amount;
    }

    public TotalSpentAmount( String month, BigDecimal amount ) {

        this.month = month;
        this.amount = amount;
    }

    /**
     * Getter.
     * 
     * @return the driverId
     */
    public Integer getDriverId() {

        return driverId;
    }

    /**
     * Getter.
     * 
     * @return the month
     */
    public String getMonth() {

        return month;
    }

    /**
     * Getter.
     * 
     * @return the amount
     */
    public BigDecimal getAmount() {

        return amount;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append( "TotalSpentAmount [driverId=" );
        builder.append( driverId );
        builder.append( ", month=" );
        builder.append( month );
        builder.append( ", amount=" );
        builder.append( amount );
        builder.append( "]" );
        return builder.toString();
    }

//    /**
//     * Add amount.
//     * 
//     * @param key
//     *            the month in format yyyy-MM
//     * 
//     * @param amount
//     *            an amount as (price * volume).
//     */
//    public void add( String key,
//            BigDecimal amount ) {
//
//        this.amount = this.amount.add( amount );
//    }
}
