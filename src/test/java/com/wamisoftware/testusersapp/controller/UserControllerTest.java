package com.wamisoftware.testusersapp.controller;

import com.wamisoftware.testusersapp.Application;
import com.wamisoftware.testusersapp.model.Address;
import com.wamisoftware.testusersapp.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test class for testing UserController.
 *
 * @author Kateryna Mironova
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    /**
     * Synchronous client to perform HTTP request.
     */
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Http port of application that is injected at runtime
     */
    @LocalServerPort
    private int port;

    /**
     * Root URL to application
     *
     * @return string with root URL on local host
     */
    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    /**
     * Tests finding all users from the database.
     */
    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(getRootUrl() + "/all", HttpMethod.GET, entity,
                        String.class);
        assertNotNull(response.getBody());
    }

    /**
     * Tests finding existent user by id.
     */
    @Test
    public void testFindUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/1", User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }

    /**
     * Tests finding non-existent user by id.
     */
    @Test
    public void testFindNonExistentUserById() {
        long id = 10_000;
        User user = restTemplate.getForObject(getRootUrl() + "/" + id,
                User.class);
        assertNull(user.getId());
    }

    /**
     * Tests creating new user.
     */
    @Test
    public void testCreateUser() {
        User user = prepareUser();
        ResponseEntity<User> postResponse = restTemplate
                .postForEntity(getRootUrl(), user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    /**
     * Creates and prepares user for testing.
     *
     * @return new user with test data
     */
    private User prepareUser() {
        User user = new User();
        user.setFirstName("New");
        user.setLastName("User");
        user.setLogin("newuser@gmail.com");
        user.setPassword("hellox users");
        user.setBirthday(new Date());
        user.setDescription("New test user");
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setCity("Kharkiv");
        address.setHomeNumber("12");
        address.setStreet("Sumska");
        user.setAddress(address);
        return user;
    }

    /**
     * Tests update existing user.
     */
    @Test
    public void testUpdateUser() {
        int id = 1;
        User user = restTemplate
                .getForObject(getRootUrl() + "/" + id, User.class);
        String updatedFirstName = "UpdatedUser";
        user.setFirstName(updatedFirstName);
        restTemplate.put(getRootUrl() + "/" + id, user);
        User updatedUser = restTemplate
                .getForObject(getRootUrl() + "/" + id, User.class);
        assertNotNull(updatedUser);
    }

    /**
     * Tests update non-existent user.
     */
    @Test
    public void testUpdateNonExistentUser() {
        long id = 10_000;
        User user = prepareUser();
        user.setId(id);
        try {
            restTemplate.put(getRootUrl() + "/" + id, user);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Tests delete existing user.
     */
    @Test
    public void testDeleteUser() {
        int id = 2;
        User user = restTemplate
                .getForObject(getRootUrl() + "/" + id, User.class);
        assertNotNull(user);
        restTemplate.delete(getRootUrl() + "/" + id);
        try {
            user = restTemplate
                    .getForObject(getRootUrl() + "/" + id, User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Tests delete non-existent user.
     */
    @Test
    public void testDeleteNonExistentUser() {
        long id = 10_000;
        User user = prepareUser();
        user.setId(id);
        try {
            restTemplate.delete(getRootUrl() + "/" + id);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Tests finding user by his name and surname.
     */
    @Test
    public void testFindByFirstNameAndLastName() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String firstName = "First";
        String lastName = "User";
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl()
                                + "/name?firstName=" + firstName + "&lastName="
                                + lastName, HttpMethod.GET, entity,
                        String.class);
        assertNotNull(response.getBody());
    }

    /**
     * Tests finding users with specified address
     */
    @Test
    public void testFindByAddressNotNull() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl()
                        + "/withAddresses", HttpMethod.GET, entity,
                String.class);
        assertNotNull(response.getBody());
    }

    /**
     * Tests finding users whose birthday is earlier than specified date.
     */
    @Test
    public void testFindByBirthdayLessThan() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String dateInRightFormat = "2000-03-20";
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl()
                        + "/olderThan?date=" + dateInRightFormat,
                HttpMethod.GET, entity,
                String.class);
        assertNotNull(response.getBody());
    }

    /**
     * Test "sending message" to users whose email is Gmail.
     */
    @PostMapping("/messaging")
    public void testSendMessageToGmailUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl()
                        + "/messaging", HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
