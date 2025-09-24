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
@Table(name = "mas_village")
public class MasVillage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_id")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "village_code",nullable = false,length = 5)
    private String code;

    @NotNull
    @Size(max = 105)
    @Column(name = "village_name",nullable = false,length = 105)
    private String name;

    @NotNull
    @Size(max = 105)
    @Column(name = "village_name_kannada",nullable = false,length = 105)
    private String nameInKannada;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 105)
    @Column(updatable = false, length = 105)
    private String createdBy;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

//    @NotNull
    @Size(max = 105)
    @Column(length = 105)
    private String lastCngBy;

    @UpdateTimestamp
    private LocalDateTime lastCngDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taluka_id",referencedColumnName = "taluka_id",nullable = false)
    private MasTaluka taluka;
}
