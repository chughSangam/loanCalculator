package com.lendico.loanCalculator;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanScheduleRequestDTO {

    @NotNull
    private Long loanAmount;

    @NotNull
    private Float nominalRate;

    @NotNull
    private Integer duration;

    @NotNull
    private LocalDateTime startDate;

}
