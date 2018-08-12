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
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Fuel Consumption data.
 * 
 * @since 2018.08.11
 * @author Andre.B.Nikitin
 */
@Entity
@Table( name = "FUEL_CONSUMPTION" )
public class FuelConsumption {

    @Column
    @Enumerated( EnumType.STRING )
    private FuelType fueltType;

    @Column
    private Float fieldName;

    @Column
    private Float vloumes;

    @Column
    private LocalDate date;

    @Column
    private Integer driveId;

    /**
     * Getter.
     * 
     * @return the fueltType
     */
    public FuelType getFueltType() {

        return fueltType;
    }

    /**
     * Setter.
     * 
     * @param fueltType
     *            the fueltType to set
     */
    public void setFueltType( FuelType fueltType ) {

        this.fueltType = fueltType;
    }

    /**
     * Getter.
     * 
     * @return the fieldName
     */
    public Float getFieldName() {

        return fieldName;
    }

    /**
     * Setter.
     * 
     * @param fieldName
     *            the fieldName to set
     */
    public void setFieldName( Float fieldName ) {

        this.fieldName = fieldName;
    }

    /**
     * Getter.
     * 
     * @return the vloumes
     */
    public Float getVloumes() {

        return vloumes;
    }

    /**
     * Setter.
     * 
     * @param vloumes
     *            the vloumes to set
     */
    public void setVloumes( Float vloumes ) {

        this.vloumes = vloumes;
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
    public Integer getDriveId() {

        return driveId;
    }

    /**
     * Setter.
     * 
     * @param driveId
     *            the driveId to set
     */
    public void setDriveId( Integer driveId ) {

        this.driveId = driveId;
    }

}
