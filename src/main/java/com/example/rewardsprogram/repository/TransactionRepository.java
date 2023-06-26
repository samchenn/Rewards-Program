package com.example.rewardsprogram.repository;

import com.example.rewardsprogram.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Use Spring Data JPA to perform DAO
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Find all transactions of each customer in a time range
     * @param customerId The customer id
     * @param start The starting date
     * @param end The ending date
     * @return The list of transactions
     */
    List<Transaction> findByCustomerIdAndTransactionDateBetween (Long customerId, LocalDateTime start, LocalDateTime end);
}
