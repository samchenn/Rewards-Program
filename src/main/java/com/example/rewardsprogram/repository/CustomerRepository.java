package com.example.rewardsprogram.repository;

import com.example.rewardsprogram.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Use Spring Data JPA to perfom DAO
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
