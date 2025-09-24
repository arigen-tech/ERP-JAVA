package com.erp.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasTalukaResponse {

    private Long id;
    private String code;
    private String name;
    private  Long districtId;
    private String status;
}
