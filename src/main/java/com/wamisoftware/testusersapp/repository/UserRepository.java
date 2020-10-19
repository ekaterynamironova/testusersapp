package com.wamisoftware.testusersapp.repository;

import com.wamisoftware.testusersapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class performing operations with the users table in database.
 *
 * @author Kateryna Mironova
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds all users that have specified first name and last name.
     *
     * @param firstName string with user name
     * @param lastName  string with user surname
     * @return list of users with specified name and surname
     */
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Finds all users who has email in Gmail.
     *
     * @return stream of users who has email in Gmail
     */
    @Query("select u from User u where u.login like '%@gmail.com%'")
    Stream<User> findWhereLoginIsGmail();

    /**
     * Finds all users who specified their address in the system.
     *
     * @return list of users with addresses
     */
    List<User> findByAddressNotNull();

    /**
     * Finds all users whose whose birthday is earlier than the specified date.
     * @param date date to compare with
     *
     * @return list of users birthday is earlier than the specified date
     */
    List<User> findByBirthdayLessThan(Date date);
}
