package com.erp.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MasHolidayRequest {

    @NotBlank(message = "Holiday name is required")
    @Size(max = 100, message = "Holiday name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    private Integer year;

    @NotNull(message = "Holiday date is required")
//    @FutureOrPresent(message = "Holiday date cannot be in the past")
    private LocalDate holidayDate;

    @NotBlank(message = "RH flag is required")
    @Pattern(regexp = "YES|NO", message = "RH must be either YES or NO")
    private String rh;

}
