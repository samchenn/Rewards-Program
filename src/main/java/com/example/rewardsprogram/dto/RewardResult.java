package com.example.rewardsprogram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@AllArgsConstructor
public class RewardResult {

    private Long customerId;
    private List<MonthlyRewards> monthlyRewardsList;
    private Integer totalRewards;

}
