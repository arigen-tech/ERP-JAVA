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
@Table(name = "mas_unit_of_measurement")
public class MasCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "unit_code",nullable = false,length = 20)
    private String code;

    @NotNull
    @Size(max = 50)
    @Column(name = "unit_name",nullable = false,length = 50)
    private String name;


    @Size(max = 20)
    @Column(name = "unit_type",length = 20)
    private String type;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 50)
    @Column(updatable = false, length = 50)
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
