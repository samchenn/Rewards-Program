package com.example.rewardsprogram.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class MonthlyRewards {
    private Long customerId;
    private String yearMonth;
    private Integer rewards;

}
