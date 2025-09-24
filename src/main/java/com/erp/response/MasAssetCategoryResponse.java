package com.erp.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MasAssetCategoryResponse {

    private Long id;
    private String code;
    private String name;
    private String type;
    private BigDecimal depreciation;
    private String description;
    private String remarks;
    private String status;
}
