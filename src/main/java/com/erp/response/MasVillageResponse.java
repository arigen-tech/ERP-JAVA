package com.erp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasVillageResponse {

    private Long id;
    private String code;
    private String name;
    private Long talukaId;
    private String status;
}
