package com.erp.entity;

import jakarta.persistence.*;
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
@Table(name = "mas_external_agency_type")
public class MasExternalAgencyType {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mas_external_agency_type_id")
    private Long id;

    @Size(max = 25)
    @Column(name = "mas_external_agency_type_code",nullable = false,length = 25)
    private String code;

    @Size(max = 50)
    @Column(name = "mas_external_agency_type_name",nullable = false,length = 50)
    private String name;

    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

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
