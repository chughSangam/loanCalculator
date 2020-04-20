package com.lendico.loanCalculator;

import com.lendico.loanCalculator.service.LoanScheduleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class LoanCalculatorApplication {

    public static void main(String[] args) {
//        LoanScheduleRequestDTO loanScheduleRequestDTO = LoanScheduleRequestDTO.builder()
//                .duration(24)
//                .loanAmount(5000l)
//                .nominalRate(5.00f)
//                .startDate(LocalDateTime.now().minusDays(1))
//                .build();
//        LoanScheduleService loanScheduleService = new LoanScheduleService();
//        loanScheduleService.calculateSchedules(loanScheduleRequestDTO);
        SpringApplication.run(LoanCalculatorApplication.class, args);
    }

}
