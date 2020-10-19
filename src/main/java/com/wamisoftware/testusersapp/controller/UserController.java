package com.wamisoftware.testusersapp.controller;

import com.wamisoftware.testusersapp.model.User;
import com.wamisoftware.testusersapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controller to interact with the users from web.
 *
 * @author Kateryna Mironova
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service for user management.
     */
    @Autowired
    private UserService userService;

    /**
     * Returns all users from database.
     *
     * @return list of users from database
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Returns user who has specified id.
     * @param userId id of user to find
     * @return user who has specified id or not found message if there is
     *         no user with this id
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        return userOptional.map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates new user and saves his data to database.
     *
     * @param user user to insert to database
     * @return the saved user
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Updates user from database.
     * @param userId id of user in database
     * @param userDetails data of updated user
     * @return updated user if there is user with specified or not found message
     *         if there is no user with this id
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
            @Valid @RequestBody User userDetails) {
        Optional<User> userOptional = userService.findById(userId);
        return userOptional.map(user -> {
            userDetails.setId(userOptional.get().getId());
            userService.save(userDetails);
            return ResponseEntity.ok().body(userDetails);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Removes user from database.
     * @param userId id of user in database
     * @return removed user if there is user with specified or not found message
     *         if there is no user with this id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        return userOptional.map(user -> {
            userService.delete(userOptional.get());
            return ResponseEntity.ok().body(user);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Finds all users that have specified first name and last name.
     *
     * @param firstName string with user name
     * @param lastName  string with user surname
     * @return list of users with specified name and surname
     */
    @GetMapping("/name")
    public List<User> findByFirstNameAndLastName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return userService.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Finds all users who specified their address in the system.
     *
     * @return list of users with addresses
     */
    @GetMapping("/withAddresses")
    public List<User> findByAddressNotNull() {
        return userService.findByAddressNotNull();
    }

    /**
     * Finds all users whose whose birthday is earlier than the specified date.
     * @param date date to compare with
     *
     * @return list of users birthday is earlier than the specified date
     */
    @GetMapping("/olderThan")
    public List<User> findByBirthdayLessThan(@RequestParam("date")
                        @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return userService.findByBirthdayLessThan(date);
    }

    /**
     * "Sends message" to users whose email is Gmail.
     */
    @PostMapping("/messaging")
    public ResponseEntity<User> sendMessageToGmailUsers() {
        userService.sendMessageToGmailUsers();
        return ResponseEntity.ok().build();
    }
}