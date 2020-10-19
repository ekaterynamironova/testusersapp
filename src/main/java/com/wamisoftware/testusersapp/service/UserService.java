package com.wamisoftware.testusersapp.service;

import com.wamisoftware.testusersapp.model.User;
import com.wamisoftware.testusersapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Service class to interact with the user
 *
 * @author Kateryna Mironova
 */
@Service
public class UserService {

    /**
     * Repository to interact with users table in database.
     */
    @Autowired
    private final UserRepository userRepository;

    /**
     * Constructor for repository initialization.
     *
     * @param userRepository repository that interacts with users table
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves user to database.
     *
     * @param user user to insert to database
     * @return the saved user
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Returns all users from database.
     *
     * @return list of users from database
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Returns user who has specified id.
     *
     * @return user who has specified id or null if there is no user
     *         with this id.
     */
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Finds all users that have specified first name and last name.
     *
     * @param firstName string with user name
     * @param lastName  string with user surname
     * @return list of users with specified name and surname
     */
    public List<User> findByFirstNameAndLastName(String firstName,
            String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Finds all users who specified their address in the system.
     *
     * @return list of users with addresses
     */
    public List<User> findByAddressNotNull() {
        return userRepository.findByAddressNotNull();
    }

    /**
     * Finds all users whose whose birthday is earlier than the specified date.
     * @param date date to compare with
     *
     * @return list of users birthday is earlier than the specified date
     */
    public List<User> findByBirthdayLessThan(Date date) {
        return userRepository.findByBirthdayLessThan(date);
    }

    /**
     * Removes user from database.
     *
     * @param user user to remove from database
     */
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * "Sends message" to specified email address (example of logic method).
     *
     * @param email valid email address
     */
    private void sendMessage(String email) {
        System.out.println("Sending message to email " + email);
    }

    /**
     * "Sends message" to users whose email is Gmail.
     */
    @Transactional(readOnly = true)
    public void sendMessageToGmailUsers() {
        try (Stream<User> gmailUsers = userRepository.findWhereLoginIsGmail()) {
            gmailUsers.forEach(user -> sendMessage(user.getLogin()));
        }
    }
}
