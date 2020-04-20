package com.lendico.loanCalculator.service;

import com.lendico.loanCalculator.LoanSchedule;
import com.lendico.loanCalculator.LoanScheduleRequestDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class LoanScheduleService {

    private static final int NO_OF_DAYS_IN_MONTH = 30;
    private static final int NO_OF_DAYS_IN_YEAR = 360;

    public static double calculateFixedAnnuity(
            double loanAmount, int termInMonths, double interestRate) {


        double monthlyRate = interestRate / 12.0;

        double annuityAmount = (loanAmount * monthlyRate) /
                (1 - Math.pow((1 + monthlyRate), -termInMonths));

        return formattedDoubleValue(annuityAmount);
    }

    private static double formattedDoubleValue(double value) {
        BigDecimal decimalValue = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        return decimalValue.doubleValue();
    }

    private static double calculateInterest(final double interestRate,
                                            final double loanAmount) {
        final double interest = (interestRate * NO_OF_DAYS_IN_MONTH * loanAmount) / NO_OF_DAYS_IN_YEAR;

        return formattedDoubleValue(interest);

    }

    public List<LoanSchedule> calculateSchedules(@NonNull final LoanScheduleRequestDTO request) {

        final double interestRate = request.getNominalRate() / 100.0;
        final double initialLoanAmount = request.getLoanAmount();
        final double annuityAmount = calculateFixedAnnuity(initialLoanAmount,
                request.getDuration(), interestRate);
        final LocalDateTime startDate = request.getStartDate();

        double currentLoanAmount;
        final List<LoanSchedule> list = new ArrayList<>();
        LoanSchedule currentSchedule = null;
        for (int i = 0; i < request.getDuration(); i++) {
            currentLoanAmount = ofNullable(currentSchedule)
                    .map(LoanSchedule::getRemainingOutstandingPrincipal)
                    .orElse(initialLoanAmount);
            currentSchedule = getLoanSchedule(startDate.plusMonths(i), interestRate, currentLoanAmount, annuityAmount);
            list.add(currentSchedule);
        }

        return list;
    }

    private LoanSchedule getLoanSchedule(@NonNull final LocalDateTime date, double interestRate, double initialLoanAmount, double annuityAmount) {
        final double interest = calculateInterest(interestRate, initialLoanAmount);
        double principal = formattedDoubleValue(annuityAmount - interest);
        principal = principal > initialLoanAmount ? initialLoanAmount : principal;
        final double loanAmountPending = formattedDoubleValue(initialLoanAmount - principal);
        final double borrowerPaymentAmount = formattedDoubleValue(principal + interest);

        return LoanSchedule.builder()
                .borrowerPaymentAmount(borrowerPaymentAmount)
                .principal(principal)
                .interest(interest)
                .initialOutstandingPrincipal(initialLoanAmount)
                .remainingOutstandingPrincipal(loanAmountPending < 0 ? 0 : loanAmountPending)
                .date(date)
                .build();
    }
}


