package com.wamisoftware.testusersapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

/**
 * Class representing the user entity in the system.
 *
 * @author Kateryna Mironova
 */
@Entity
@Table(name = "users", schema = "users_schema")
public class User {

    /**
     * Unique user identifier in the system.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the user.
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Last name of the user.
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Date of user birth.
     */
    @Past
    @Column(nullable = false)
    private Date birthday;

    /**
     * Login in form of email which is unique for every user.
     */
    @Email
    @Size(min = 5)
    @Column(nullable = false, unique = true)
    private String login;

    /**
     * User's password in the system.
     */
    @Size(min = 5)
    @Column(nullable = false)
    private String password;

    /**
     * Description of the user with additional information.
     */
    @Size(max = 25000)
    @Column
    private String description;

    /**
     * Address of the user.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Function to get user identifier {@link User#id}.
     *
     * @return positive number of user identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * User identifier {@link User#id} determination procedure.
     *
     * @param id positive number
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Function to get user first name {@link User#firstName}.
     *
     * @return string with user name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * User first name {@link User#firstName} determination procedure.
     *
     * @param firstName string with user name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Function to get user last name {@link User#lastName}.
     *
     * @return string with user surname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * User last name {@link User#lastName} determination procedure.
     *
     * @param lastName string with surname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Function to get user date of birth {@link User#birthday}.
     *
     * @return date of user's birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * User's birthday {@link User#birthday} determination procedure.
     *
     * @param birthday date of user's birthday (date has to be earlier than
     *                 current date)
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Function to get user's login {@link User#login}.
     *
     * @return string with user's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * User's login {@link User#login} determination procedure.
     *
     * @param login string with user's login in form of email which is at least
     *              5 characters long
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Function to get user's password {@link User#password}.
     *
     * @return string with user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * User's password {@link User#password} determination procedure.
     *
     * @param password string with user's password which is at least 5
     *                 characters long
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Function to get user's description (from field "About me")
     * {@link User#description}.
     *
     * @return string with user's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * User's description {@link User#description} determination procedure.
     *
     * @param description string with user's description which length is
     *                    no more than 25000 characters
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Function to get user's address {@link User#address}.
     *
     * @return class with user's address data
     */
    public Address getAddress() {
        return address;
    }

    /**
     * User's address {@link User#address} determination procedure.
     *
     * @param address class with address data
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id.equals(user.id) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && birthday
                .equals(user.birthday) && login.equals(user.login) && password
                .equals(user.password) && Objects
                .equals(description, user.description) && Objects
                .equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, login, password,
                description, address);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", birthday=" + birthday
                + ", login='" + login + '\'' + ", password='" + password + '\''
                + ", description='" + description + '\'' + ", address="
                + address + '}';
    }
}
