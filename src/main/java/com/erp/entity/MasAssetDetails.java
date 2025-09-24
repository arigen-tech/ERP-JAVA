package com.erp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "mas_asset_details")
public class MasAssetDetails {

    private Long assetId;
    private LocalDate dateOfProfession;
    private LocalDate dateOfRegistration;
    private BigDecimal assetCost;
    private BigDecimal otherCharges;
    private BigDecimal totalCost;
    private BigDecimal presentBookValue;
    private String remarks;
    private String document;
    private MasCentre centre;
    private MasAssetCategory assetCategory;
    private MasAssetStatus assetStatus;

}
