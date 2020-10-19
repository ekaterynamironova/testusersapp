package com.wamisoftware.testusersapp.repository;

import com.wamisoftware.testusersapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class performing operations with the addresses table in database.
 *
 * @author Kateryna Mironova
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Finds all addresses in Ukraine.
     *
     * @return list of addresses in Ukraine
     */
    @Query("select a from Address a where a.country is 'Ukraine'")
    List<Address> findWhereCountryIsUkraine();
}
