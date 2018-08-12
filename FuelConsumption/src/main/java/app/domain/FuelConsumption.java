/**
 * Project ..... FuelConsumption<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-08-11<br>
 * <br>
 */
package app.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Fuel Consumption data.
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@Entity
@Table( name = "FUEL_CONSUMPTION" )
public class FuelConsumption {

    @Id
    @GeneratedValue
    @Column
    private Long gid;

    @Column
    @Convert( converter = FuelType.Converter.class )
    private FuelType fuelType;

    @Column
    private Float price;

    @Column
    private Float volume;

    @Column
    @DateTimeFormat( pattern = "MM.dd.yyyy" )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "MM.dd.yyyy" )
    private LocalDate date;

    @Column
    private Integer driverId;

    /**
     * Contructor.
     */
    public FuelConsumption() {

    }

    /**
     * Contructor.
     * 
     * @param fuel
     * @param price
     * @param volume
     * @param date
     * @param driverId
     */
    public FuelConsumption( FuelType fuel, Float price, Float volume, LocalDate date, Integer driverId ) {

        fuelType = fuel;
        this.price = price;
        this.volume = volume;
        this.date = date;
        this.driverId = driverId;
    }

    /**
     * Getter.
     * 
     * @return the gid
     */
    public Long getGid() {

        return gid;
    }

    /**
     * Setter.
     * 
     * @param gid
     *            the gid to set
     */
    public void setGid( Long gid ) {

        this.gid = gid;
    }

    /**
     * Getter.
     * 
     * @return the fueltType
     */
    public FuelType getFuelType() {

        return fuelType;
    }

    /**
     * Setter.
     * 
     * @param fueltType
     *            the fueltType to set
     */
    public void setFuelType( FuelType fueltType ) {

        this.fuelType = fueltType;
    }

    /**
     * Getter.
     * 
     * @return the price
     */
    public Float getPrice() {

        return price;
    }

    /**
     * Setter.
     * 
     * @param price
     *            the price to set
     */
    public void setPrice( Float price ) {

        this.price = price;
    }

    /**
     * Getter.
     * 
     * @return the volume
     */
    public Float getVolume() {

        return volume;
    }

    /**
     * Setter.
     * 
     * @param volume
     *            the volume to set
     */
    public void setVolume( Float volume ) {

        this.volume = volume;
    }

    /**
     * Getter.
     * 
     * @return the date
     */
    public LocalDate getDate() {

        return date;
    }

    /**
     * Setter.
     * 
     * @param date
     *            the date to set
     */
    public void setDate( LocalDate date ) {

        this.date = date;
    }

    /**
     * Getter.
     * 
     * @return the driveId
     */
    public Integer getDriverId() {

        return driverId;
    }

    /**
     * Setter.
     * 
     * @param driveId
     *            the driveId to set
     */
    public void setDriverId( Integer driverId ) {

        this.driverId = driverId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append( "FuelConsumption [gid=" );
        builder.append( gid );
        builder.append( ", fuelType=" );
        builder.append( fuelType );
        builder.append( ", price=" );
        builder.append( price );
        builder.append( ", volume=" );
        builder.append( volume );
        builder.append( ", date=" );
        builder.append( date );
        builder.append( ", driverId=" );
        builder.append( driverId );
        builder.append( "]" );
        return builder.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
        result = prime * result + ( ( driverId == null ) ? 0 : driverId.hashCode() );
        result = prime * result + ( ( fuelType == null ) ? 0 : fuelType.hashCode() );
        result = prime * result + ( ( gid == null ) ? 0 : gid.hashCode() );
        result = prime * result + ( ( price == null ) ? 0 : price.hashCode() );
        result = prime * result + ( ( volume == null ) ? 0 : volume.hashCode() );
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( !( obj instanceof FuelConsumption ) ) return false;
        FuelConsumption other = (FuelConsumption) obj;
        if ( date == null ) {
            if ( other.date != null ) return false;
        } else if ( !date.equals( other.date ) ) return false;
        if ( driverId == null ) {
            if ( other.driverId != null ) return false;
        } else if ( !driverId.equals( other.driverId ) ) return false;
        if ( fuelType != other.fuelType ) return false;
        if ( gid == null ) {
            if ( other.gid != null ) return false;
        } else if ( !gid.equals( other.gid ) ) return false;
        if ( price == null ) {
            if ( other.price != null ) return false;
        } else if ( !price.equals( other.price ) ) return false;
        if ( volume == null ) {
            if ( other.volume != null ) return false;
        } else if ( !volume.equals( other.volume ) ) return false;
        return true;
    }

}
