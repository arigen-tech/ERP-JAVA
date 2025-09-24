package com.erp.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_holiday")
public class MasHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "holiday_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Column(name = "year",nullable = false,length = 4)
    private Integer year;

    @NotNull
    private LocalDate holidayDate;

    @NotNull
    @Size(max = 3)
    @Column(name = "rh",nullable = false,length = 3)
    private String rh;                                     //Yes or No

    @NotNull
    @Size(max = 1)
    @Column(name = "status",nullable = false,length = 1)
    private String status;

    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
