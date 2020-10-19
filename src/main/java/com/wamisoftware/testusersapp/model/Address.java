package com.wamisoftware.testusersapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class representing the address of user in the system.
 *
 * @author Kateryna Mironova
 */
@Entity
@Table(name = "addresses", schema = "users_schema")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Address {

    /**
     * Unique address identifier in the system.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the country in address.
     */
    @Column(nullable = false)
    private String country;

    /**
     * Name of the city in the country.
     */
    @Column(nullable = false)
    private String city;

    /**
     * Name of the street in the city.
     */
    @Column(nullable = false)
    private String street;

    /**
     * Number of the home in the city.
     */
    @Column(name = "home_number", nullable = false)
    private String homeNumber;

    /**
     * Function to get address identifier {@link Address#id}.
     *
     * @return positive number of address identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Address identifier {@link Address#id} determination procedure.
     *
     * @param id positive number
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Function to get country from address {@link Address#country}.
     *
     * @return string with name of the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Name of the country {@link Address#country} determination procedure.
     *
     * @param country string name of the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Function to get city from address {@link Address#city}.
     *
     * @return string with name of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Name of the city {@link Address#city} determination procedure.
     *
     * @param city string name of the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Function to get street from address {@link Address#street}.
     *
     * @return string with name of the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Name of the street {@link Address#street} determination procedure.
     *
     * @param street string with name of the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Function to get number of home from address {@link Address#homeNumber}.
     *
     * @return string with number of home
     */
    public String getHomeNumber() {
        return homeNumber;
    }

    /**
     * Number of home {@link Address#homeNumber} determination procedure.
     *
     * @param homeNumber string with number of home
     */
    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    /**
     * Default constructor.
     */
    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return id.equals(address.id) && country.equals(address.country) && city
                .equals(address.city) && street.equals(address.street)
                && homeNumber.equals(address.homeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, homeNumber);
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", country='" + country + '\''
                + ", city='" + city + '\'' + ", street='" + street + '\''
                + ", homeNumber='" + homeNumber + '\'' + '}';
    }
}
