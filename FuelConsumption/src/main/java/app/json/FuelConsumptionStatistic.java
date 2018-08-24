/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-23<br>
 * <br>
 */
package app.json;

import java.math.BigDecimal;

import app.domain.FuelType;

/**
 * 
 * @since 2018.08.23
 * @author Andre.B.Nikitin
 */
public class FuelConsumptionStatistic {

    private int year;

    private int month;

    private FuelType fuelType;

//    @Digits( fraction = 2, integer = 1000000 )
    private BigDecimal sumVolume;

    private double avgPrice;

    private BigDecimal totalAmount;

    /**
     * Contructor.
     */
    public FuelConsumptionStatistic() {

    }

    public FuelConsumptionStatistic( int year, int month, FuelType fuelType, BigDecimal sumVolume,
            double avgPrice, BigDecimal totalAmount ) {

        super();
        this.year = year;
        this.month = month;
        this.fuelType = fuelType;
        this.sumVolume = sumVolume;
        this.avgPrice = avgPrice;
        this.totalAmount = totalAmount;
    }

    /**
     * Getter.
     * 
     * @return the year
     */
    public int getYear() {

        return year;
    }

    /**
     * Setter.
     * 
     * @param year
     *            the year to set
     */
    public void setYear( int year ) {

        this.year = year;
    }

    /**
     * Getter.
     * 
     * @return the month
     */
    public int getMonth() {

        return month;
    }

    /**
     * Setter.
     * 
     * @param month
     *            the month to set
     */
    public void setMonth( int month ) {

        this.month = month;
    }

    /**
     * Getter.
     * 
     * @return the fuelType
     */
    public FuelType getFuelType() {

        return fuelType;
    }

    /**
     * Setter.
     * 
     * @param fuelType
     *            the fuelType to set
     */
    public void setFuelType( FuelType fuelType ) {

        this.fuelType = fuelType;
    }

    /**
     * Getter.
     * 
     * @return the sumVolume
     */
    public BigDecimal getSumVolume() {

        return sumVolume;
    }

    /**
     * Setter.
     * 
     * @param sumVolume
     *            the sumVolume to set
     */
    public void setSumVolume( BigDecimal sumVolume ) {

        this.sumVolume = sumVolume;
    }

    /**
     * Getter.
     * 
     * @return the avgPrice
     */
    public double getAvgPrice() {

        return avgPrice;
    }

    /**
     * Setter.
     * 
     * @param avgPrice
     *            the avgPrice to set
     */
    public void setAvgPrice( double avgPrice ) {

        this.avgPrice = avgPrice;
    }

    /**
     * Getter.
     * 
     * @return the avgTotalAmount
     */
    public BigDecimal getTotalAmount() {

        return totalAmount;
    }

    /**
     * Setter.
     * 
     * @param totalAmount
     *            the avgTotalAmount to set
     */
    public void setTotalAmount( BigDecimal totalAmount ) {

        this.totalAmount = totalAmount;
    }

}
