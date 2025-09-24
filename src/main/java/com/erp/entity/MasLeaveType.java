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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hr_mas_leave_type")
public class MasLeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Long id;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Size(max = 100)
    @Column(length = 100)
    private String updatedBy;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 1)
    @Column(length = 1)
    private String encashable;

    @NotNull
    @Column(nullable = false)
    private Integer allowedDays;

    @NotNull
    @Column(nullable = false)
    private LocalDate validFromDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate validToDate;

    @Size(max = 200)
    @Column(length = 200)
    private String remarks;


    @Column(precision = 5,scale = 2)
    private BigDecimal encashablePercent;

    @Size(max =1)
    @Column(name = "cr_frdable",length = 1)
    private String carryForward;



    @Size(max = 1)
    @Column(nullable = false, length = 1)
    private String monthOrYear;

    @Size(max = 1)
    @Column(length = 1)
    private String halfDayAllowed;


    private Integer bufferRequired;


    @Size(max = 100)
    @Column(updatable = false, length = 100)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 60)
    @Column(length = 60)
    private String enchFormula;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_type",referencedColumnName = "leave_id")
    private MasLeave leave;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id",referencedColumnName = "centre_id")
    private MasCentre centre;




}
