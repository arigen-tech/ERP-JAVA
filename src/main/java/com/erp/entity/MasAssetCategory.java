package com.erp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_asset_category")
public class MasAssetCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_category_id")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "asset_category_code",nullable = false,length = 10)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "asset_category_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @NotNull
    @Size(max = 15)
    @Column(name = "asset_category_type",nullable = false,length = 15)
    private String type;

    @NotNull
    @Size(max = 250)
    @Column(nullable = false,length = 250)
    private String description;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false,length = 100)
    private String remarks;

    @NotNull
    @Column(precision = 4,scale = 2)
    private BigDecimal depreciation;

    @Size(max = 50)
    @Column(updatable = false,length = 50)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 50)
    @Column(length = 50)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
