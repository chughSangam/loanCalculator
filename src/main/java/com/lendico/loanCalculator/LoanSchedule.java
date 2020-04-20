package com.lendico.loanCalculator;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class LoanSchedule {

    @NonNull
    private Double borrowerPaymentAmount;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private Double initialOutstandingPrincipal;

    @NonNull
    private Double interest;

    @NonNull
    private Double principal;

    @NonNull
    private Double remainingOutstandingPrincipal;
}
