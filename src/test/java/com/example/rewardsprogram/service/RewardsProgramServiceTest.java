package com.example.rewardsprogram.service;

import com.example.rewardsprogram.dto.RewardResult;
import com.example.rewardsprogram.entity.Customer;
import com.example.rewardsprogram.entity.Transaction;
import com.example.rewardsprogram.repository.CustomerRepository;
import com.example.rewardsprogram.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RewardsProgramServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    TransactionRepository transactionRepository;
    @InjectMocks
    RewardServiceImpl rewardService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Transaction> mockList = new ArrayList<>();
        mockList.add(new Transaction(5L,  LocalDateTime.now(), 99.00, new Customer()));
        mockList.add(new Transaction(1L,  LocalDateTime.now(), 100.00, new Customer()));
        mockList.add(new Transaction(2L,  LocalDateTime.now(), 200.00, new Customer()));
        mockList.add(new Transaction(3L,  LocalDateTime.now(), 60.00, new Customer()));
        mockList.add(new Transaction(4L,  LocalDateTime.now(), 50.00, new Customer()));
        mockList.add(new Transaction(5L,  LocalDateTime.now(), 0.00, new Customer()));

        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(any(), any(), any()))
                .thenReturn(mockList);
        when(customerRepository.findById(any())).thenReturn(Optional.of(new Customer(1L, "user1")));
    }

    /**
     * Test calculate rewards.
     */
    @Test
    public void calculateRewards() {

        RewardResult rewardResult = rewardService.calculateRewards(1L, LocalDateTime.now(), LocalDateTime.now());

        assertEquals(Optional.ofNullable(rewardResult.getTotalRewards()), Optional.of(359));

    }

    /**
     * Test calculate rewards till now.
     */
    @Test
    public void calculateRewardsTillNow() {

        RewardResult rewardResult = rewardService.calculateRewardsTillNow(1L);

        assertEquals(Optional.ofNullable(rewardResult.getTotalRewards()), Optional.of(359));

    }

}
