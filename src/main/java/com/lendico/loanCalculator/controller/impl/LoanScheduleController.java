package com.lendico.loanCalculator.controller.impl;

import com.lendico.loanCalculator.LoanSchedule;
import com.lendico.loanCalculator.LoanScheduleRequestDTO;
import com.lendico.loanCalculator.controller.LoanScheduleAPI;
import com.lendico.loanCalculator.service.LoanScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LoanScheduleController implements LoanScheduleAPI {

    @Autowired
    private LoanScheduleService loanScheduleService;

    @Override
    public List<LoanSchedule> calculateLoanSchedule(@Valid LoanScheduleRequestDTO loanScheduleRequestDTO) {
        return loanScheduleService.calculateSchedules(loanScheduleRequestDTO);
    }
}
