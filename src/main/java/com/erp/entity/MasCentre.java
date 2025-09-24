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
@Table(name = "mas_centre")//Replace mas_hospital in running project
public class MasCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "centre_id")
    private  Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "centre_code",nullable = false,length = 100)
    private String code;

    @NotNull
    @Size(max = 200)
    @Column(name = "centre_name",nullable = false,length = 200)
    private String name;

    @NotNull
    @Size(max = 200)
    @Column(name = "centre_name_kannada",nullable = false,length = 200)
    private String nameInKannada;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @NotNull
    @Size(max = 15)
    @Column(nullable = false,length = 200)
    private String contactNumber;

    @NotNull
    @Size(max = 300)
    @Column(nullable = false,length = 300)
    private String address;

    @NotNull
    @Size(max = 20)
    @Column(name = "centre_type",nullable = false,length = 20)
    private String type;

    @NotNull
    @Column(name = "storage_capacity",nullable = false,precision = 12,scale = 2)
    private BigDecimal processingCapacity;

    @NotNull
    @Column(name = "goDown_capacity",nullable = false,precision = 12,scale = 2)
    private BigDecimal goDownCapacity;

    @NotNull
    @Column(name = "hra",nullable = false,precision = 5,scale = 2)
    private BigDecimal hraPercentage;

    @NotNull
    @Size(max = 30)
    @Column(nullable = false,length = 30)
    private String bankRecipentCode;

    @Size(max = 100)
    @Column(updatable = false,length = 100)
    private  String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 100)
    @Column(length = 100)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id",referencedColumnName = "unit_id")
    private MasCapacity uom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",referencedColumnName = "district_id")
    private MasDistrict district;
}
