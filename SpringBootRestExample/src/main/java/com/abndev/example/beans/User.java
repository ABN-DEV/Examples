/**
 * Project ..... SpringBootRestExample<br>
 * <br>
 * Author ...... Andrey.B.Nikitin<br>
 * E-Mail ...... ABN.Java@GMail.com<br>
 * Created ..... 2018-06-01<br>
 * <br>
 */
package com.abndev.example.beans;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * User bean.
 * 
 * @since 2018.06.01
 * @author annik
 */
public class User {

    private Integer gid;

    @Size( min = 2, message = "Firstname must have atleast 2 characters" )
    private String firstname;

    @Size( min = 2, message = "Lastname must have atleast 2 characters" )
    private String lastname;

    @Past( )
    private LocalDate birthDate;

    public Integer getGid() {

        return gid;
    }

    public void setGid( Integer gid ) {

        this.gid = gid;
    }

    public String getFirstname() {

        return firstname;
    }

    public void setFirstname( String firstname ) {

        this.firstname = firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public void setLastname( String lastname ) {

        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {

        return birthDate;
    }

    public void setBirthDate( LocalDate birthDate ) {

        this.birthDate = birthDate;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append( "User [gid=" );
        builder.append( gid );
        builder.append( ", firstname=" );
        builder.append( firstname );
        builder.append( ", lastname=" );
        builder.append( lastname );
        builder.append( ", birthDate=" );
        builder.append( birthDate );
        builder.append( "]" );
        return builder.toString();
    }

    /**
     * Contructor.
     * 
     * @param gid
     * @param firstname
     * @param lastname
     * @param birthDate
     */
    public User( Integer gid, String firstname, String lastname, LocalDate birthDate ) {

        super();
        this.gid = gid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    /**
     * Contructor.
     */
    public User() {

        // TODO Auto-generated constructor stub
    }
}
