package com.hyvuonggia.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyvuonggia.ecommerce.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
