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
@Table(name = "mas_store_vendor_type")
public class MasVendorType {//Vendor Type Master in site or mas_store_supplier_type in DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "vendor_code",nullable = false,length = 20)
    private String vendorTypeCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "vendor_name",nullable = false,length = 100)
    private String vendorTypeName;

    @NotNull
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
    @Column(nullable = false,length = 50)
    private String modifiedBy;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "agency_type_id",referencedColumnName = "mas_external_agency_type_id")
    private MasExternalAgencyType agencyType;
}
