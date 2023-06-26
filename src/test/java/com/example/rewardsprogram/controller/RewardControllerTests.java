package com.example.rewardsprogram.controller;

import com.example.rewardsprogram.dto.RewardResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.rewardsprogram.service.RewardService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RewardController.class)
public class RewardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    /**
     *  Test for getRewards with a valid time range input
     * @throws Exception
     */
    @Test
    public void testGetRewardsWithTimeRange() throws Exception{
        when(rewardService.calculateRewards(any(),any(),any()))
                .thenReturn(new RewardResult(Long.valueOf(1), new ArrayList<>(), 100));
        mockMvc.perform(get("/api/v1/rewards/2?startDate=2023-07-01T18:32:09.12&endDate=2023-12-02T19:33:45.00"))
                .andExpect(status().isOk());
    }

    /**
     * Test for getRewards with invalid time range input
     * @throws Exception
     */
    @Test
    public void testGetRewardsWithInvalidTimeRange() throws Exception {
        when(rewardService.calculateRewards(any(),any(),any()))
                .thenReturn(new RewardResult(Long.valueOf(1), new ArrayList<>(), 100));
        mockMvc.perform(get("/api/v1/rewards/2?startDate=2023-06-01T18:32:09.12"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test for getRewards without time range
     * @throws Exception
     */
    @Test
    public void testGetRewardsWithoutTimeRange() throws Exception{
        when(rewardService.calculateRewardsTillNow(any()))
                .thenReturn(new RewardResult(Long.valueOf(2), new ArrayList<>(), 100));
        mockMvc.perform(get("/api/v1//rewards/2"))
                .andExpect(status().isOk());
    }
}
