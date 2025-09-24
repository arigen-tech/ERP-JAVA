package com.erp.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MasMaritalStatusResponse {

    private Long id;
    private String statusCode;
    private String name;
    private String status;
}
