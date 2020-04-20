package com.lendico.loanCalculator.controller;


import com.lendico.loanCalculator.LoanSchedule;
import com.lendico.loanCalculator.LoanScheduleRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

public interface LoanScheduleAPI {

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"},
            value = {"/loan/schedules/fetch"}
    )
    List<LoanSchedule> calculateLoanSchedule(@RequestBody @Valid LoanScheduleRequestDTO loanScheduleRequestDTO);
}
