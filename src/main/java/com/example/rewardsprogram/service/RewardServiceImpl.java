package com.example.rewardsprogram.service;

import com.example.rewardsprogram.dto.MonthlyRewards;
import com.example.rewardsprogram.dto.RewardResult;
import com.example.rewardsprogram.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rewardsprogram.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService {
    private TransactionRepository transactionRepository;

    private static final double FIRST_REWARD_THRESHOLD = 50.00;
    private static final double SECOND_REWARD_THRESHOLD = 100.00;

    @Autowired
    public RewardServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Calculate the rewards points for each customer in a time range
     *
     * @param customerId the customer id
     * @param startDate  the starting date to retrieve data
     * @param endDate    the ending date to retrieve data
     * @return The reward points earned for each customer per month and total
     */
    @Override
    public RewardResult calculateRewards(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {

        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

        return getRewardResult(customerId, transactions);

    }

    /**
     * Calculate the rewards points for each customer till now (with a three-month time range for efficiency purpose)
     *
     * @param customerId the customer id
     * @return The reward points earned for each customer per month and total
     */
    @Override
    public RewardResult calculateRewardsTillNow(Long customerId) {

        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(3);

        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

        return getRewardResult(customerId, transactions);

    }

    /**
     * Calculate the rewards points for each transaction.
     *
     * @param customerId The customer id
     * @param transactions The list of transactions
     * @return The reward points earned for each customer per month and total
     */
    private RewardResult getRewardResult(Long customerId, List<Transaction> transactions) {
        Integer totalRewards = 0;
        HashMap<String, Integer> monthlyRewards = new HashMap<>();

        // iterate all transactions and calculate the rewards points
        for (Transaction transaction : transactions) {

            LocalDateTime transactionDate = transaction.getTransactionDate();
            Double transactionValue = transaction.getTransactionValue();

            // format transactionDate into year-month string format, e.g. 2020-01, put it into monthlyRewards HashMap
            String yearMonth = transactionDate.getYear() + "-" + transactionDate.getMonthValue();

            // calculate the rewards points for each transaction
            int rewards = calculateRewardPoints(transactionValue);

            // put the rewards points into monthlyRewards HashMap
            monthlyRewards.put(yearMonth, monthlyRewards.getOrDefault(yearMonth, 0) + rewards);

            // calculate the total rewards points
            totalRewards += rewards;

        }

        // return the result
        List<MonthlyRewards> monthlyRewardsList = monthlyRewards.entrySet().stream()
                .map(entry -> new MonthlyRewards(customerId, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return new RewardResult(customerId, monthlyRewardsList, totalRewards);
    }

    /**
     * Calculate the rewards points for each transaction. A customer receives 2 points for every dollar spent over $100 in each transaction,
     * plus 1 point for every dollar spent over $50 in each transaction
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     *
     * @param transactionValue The transaction value
     * @return The reward points earned for each transaction
     */
    private int calculateRewardPoints(Double transactionValue) {

        int rewards = 0;

       if (transactionValue > FIRST_REWARD_THRESHOLD) {
            rewards += (int) (Math.min(transactionValue, SECOND_REWARD_THRESHOLD) - FIRST_REWARD_THRESHOLD) * 1;
           if (transactionValue > SECOND_REWARD_THRESHOLD) {
               rewards += (int) (transactionValue - SECOND_REWARD_THRESHOLD) * 2;
           }
       }
        return rewards;

    }
}
