package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
