package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_district")
public class MasDistrict {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "district_code",nullable = false, length = 50)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "district_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "dist_name_kannada",nullable = false,length = 100)
    private String nameInKannada;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 100)
    @Column(updatable = false, length = 100)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private String createdAt;

    @Size(max = 100)
    @Column(length = 100)
    private String modifiedBy;

    @UpdateTimestamp
    private String modifiedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id",referencedColumnName = "state_id",nullable = false)
    private MasState state;

}
