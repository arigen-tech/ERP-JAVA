package com.erp.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MasHolidayResponse {

    private Long id;
    private String name;
    private Integer year;
    private String rh;
    private LocalDate holidayDate;
    private String status;
}
