package com.example.rewardsprogram.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDateTime;


/**
 *  The Restful endpoint for the Rewards-Program.
 */
@RequestMapping("/api/v1/rewards")
public interface RewardController {

    /**
     * Get rewards points for each customer in a time range
     * @param customer_id the customer id
     * @param startDate the starting date to retrieve data
     * @param endDate the ending date to retrieve data
     * @return The reward points earned for each customer per month and total
     */
    @GetMapping("{customer_id}")
    public ResponseEntity<?> getRewards (@PathVariable String customer_id,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endDate);

}
