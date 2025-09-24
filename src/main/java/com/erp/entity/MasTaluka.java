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
@Table(name = "mas_taluka")
public class MasTaluka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taluka_id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "taluka_code",nullable = false,length = 50)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "taluka_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "taluka_name_kannada",nullable = false,length = 100)
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
    private LocalDateTime createdAt;

//    @NotNull
    @Size(max = 100)
    @Column(length = 100)
    private String modifiedBy;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",referencedColumnName = "district_id",nullable = false)
    private MasDistrict district;
}
