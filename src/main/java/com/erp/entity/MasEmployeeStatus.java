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

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_employee_status")
public class MasEmployeeStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_status_id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "emp_status_code",nullable = false,length = 30)
    private String code;

    @NotNull
    @Size(max = 50)
    @Column(name = "emp_status_name",nullable = false,length = 50)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 30)
    @Column(updatable = false,length = 30)
    private String createdBy;

    @Size(max = 30)
    @Column(length = 30)
    private String updatedBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
