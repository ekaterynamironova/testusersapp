package com.wamisoftware.testusersapp.service;

import com.wamisoftware.testusersapp.model.Address;
import com.wamisoftware.testusersapp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to interact with the address
 *
 * @author Kateryna Mironova
 */
@Service
public class AddressService {

    /**
     * Repository to interact with addresses table in database.
     */
    @Autowired
    private final AddressRepository addressRepository;

    /**
     * Constructor for repository initialization.
     *
     * @param addressRepository repository that interacts with addresses table
     */
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Saves address to database.
     *
     * @param address address to insert to database
     * @return the saved address
     */
    public void create(Address address) {
        addressRepository.save(address);
    }

    /**
     * Returns all addresses from database.
     *
     * @return list of addresses from database
     */
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    /**
     * Returns address who has specified id.
     *
     * @return address who has specified id or null if there is no user
     *         with this id.
     */
    public Address findById(Long addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }

    /**
     * Finds all addresses in Ukraine.
     *
     * @return list of addresses in Ukraine
     */
    public List<Address> findWhereCountryIsUkraine() {
        return addressRepository.findWhereCountryIsUkraine();
    }

    /**
     * Removes address from database.
     *
     * @param address address to remove from database
     */
    public void delete(Address address) {
        addressRepository.delete(address);
    }
}
