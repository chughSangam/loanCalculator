package com.lendico.loanCalculator.service;


import com.lendico.loanCalculator.LoanSchedule;
import com.lendico.loanCalculator.LoanScheduleRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
public class LoanScheduleServiceTest {

    @InjectMocks
    private LoanScheduleService loanScheduleService;

    @Test
    public void testCalculation() {

        LoanScheduleRequestDTO loanScheduleRequestDTO = LoanScheduleRequestDTO.builder()
                .loanAmount(5000l)
                .startDate(LocalDateTime.now().plusDays(5))
                .nominalRate(5.00f)
                .duration(24)
                .build();

        List<LoanSchedule> loanSchedules = loanScheduleService.calculateSchedules(loanScheduleRequestDTO);
        assertThat(loanSchedules, hasSize(24));
        assertThat(loanSchedules.stream()
                .filter(schedule -> schedule.getBorrowerPaymentAmount() == 219.36)
                .collect(Collectors.toList()), hasSize(23));
    }

}
