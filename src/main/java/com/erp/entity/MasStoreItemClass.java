package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name="mas_store_item_class")
public class MasStoreItemClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_class_id")
    private Long itemClassId;

    @Column(name = "item_class_code", length = 10, nullable = false)
    private String itemClassCode;

    @Column(name = "item_class_name", length = 50, nullable = false)
    private String itemClassName;

    @Column(name = "item_class_name_kann", length = 100)
    private String itemClassNameKann;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "update_on")
    private LocalDateTime updatedOn;



}
