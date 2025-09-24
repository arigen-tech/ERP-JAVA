package com.erp.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MasLeaveTypeResponse {

    private Long id;
    private String leaveType;
    private Integer allowedDays;
    private LocalDate validFromDate;
    private String carryForward;
    private String monthOrYear;
    private String status;
}
