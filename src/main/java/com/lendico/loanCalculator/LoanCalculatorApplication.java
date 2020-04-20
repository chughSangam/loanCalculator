package com.lendico.loanCalculator;

import com.lendico.loanCalculator.service.LoanScheduleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class LoanCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanCalculatorApplication.class, args);
    }

}
