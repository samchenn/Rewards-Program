package com.example.rewardsprogram.service;

import com.example.rewardsprogram.dto.RewardResult;

import java.time.LocalDateTime;

/**
 * The business logic of the Rewards-Program
 */
public interface RewardService {

    /**
     *  Calculate the rewards points for each customer in a time range
     * @param customerId the customer id
     * @param startDate the starting date to retrieve data
     * @param endDate the ending date to retrieve data
     * @return The reward points earned for each customer per month and total
     */
    RewardResult calculateRewards(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Calculate the rewards points for each customer till now (with a three-month time range for efficiency purpose)
     * @param customerId the customer id
     * @return The reward points earned for each customer per month and total
     */
    RewardResult calculateRewardsTillNow(Long customerId);

}
