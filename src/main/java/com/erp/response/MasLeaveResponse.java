package com.erp.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MasLeaveResponse {

    private Long id;
    private String description;
    private String status;
}
