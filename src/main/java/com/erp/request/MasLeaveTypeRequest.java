package com.erp.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MasLeaveTypeRequest {

    @NotNull(message = "Leave ID is required")
    private Long leaveId;

    @NotBlank(message = "Encashable flag is required (Y/N)")
    @Size(max = 1, message = "Encashable must be a single character (Y/N)")
    private String encashable;

    @NotNull(message = "Allowed days is required")
    @Min(value = 1, message = "Allowed days must be at least 1")
    private Integer allowedDays;

    @NotNull(message = "Valid from date is required")
    private LocalDate validFromDate;

    @NotNull(message = "Valid to date is required")
    private LocalDate validToDate;

    @Size(max = 200, message = "Remarks cannot exceed 200 characters")
    private String remarks;

    @Size(max = 1, message = "Carry forward must be a single character (Y/N)")
    private String carryForward;

    @Size(max = 60, message = "Encashable formula cannot exceed 60 characters")
    private String enchFormula;

    private Integer bufferRequired;

    @Digits(integer = 3, fraction = 2, message = "Encashable percent must be in format XXX.XX")
    private BigDecimal encashablePercent;

    @NotBlank(message = "Month/Year indicator is required")
    @Size(max = 1, message = "MonthOrYear must be a single character (M/Y)")
    private String monthOrYear;

    @Size(max = 1, message = "Half-day allowed must be a single character (Y/N)")
    private String halfDayAllowed;


}
