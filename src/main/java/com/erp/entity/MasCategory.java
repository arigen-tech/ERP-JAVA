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
@Table(name = "mas_category")
public class MasCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "category_code",nullable = false,length = 20)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "category_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 100)
    @Column(updatable = false, length = 100)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

    @Size(max = 100)
    @Column(length = 100)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
