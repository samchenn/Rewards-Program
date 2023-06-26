package com.example.rewardsprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.rewardsprogram.service.RewardService;

import java.time.LocalDateTime;
@RestController
public class RewardControllerImpl implements RewardController {

    private RewardService rewardService;

    @Autowired
    public RewardControllerImpl(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Get rewards points for each customer in a time range
     *
     * @param customer_id the customer id
     * @param startDate   the starting date to retrieve data
     * @param endDate     the ending date to retrieve data
     * @return The reward points earned for each customer per month and total
     */
    @Override
    public ResponseEntity<?> getRewards(String customer_id, LocalDateTime startDate, LocalDateTime endDate) {

        Long customerId = Long.valueOf(customer_id);

        if((startDate == null && endDate!= null) || (startDate != null && endDate == null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both startDate and endDate should be provided or neither of them should be provided");
        }

        if(startDate == null && endDate == null){
            return ResponseEntity.ok(rewardService.calculateRewardsTillNow(customerId));
        }

        return ResponseEntity.ok(rewardService.calculateRewards(customerId, startDate, endDate));
    }
}
