package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "mas_store_item_category") //same as Erp in DB or Item Category master in website
public class MasItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_category_id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "item_category_code",nullable = false,length = 20)
    private String code;

    @NotBlank
    @Size(max = 150)
    @Column(name = "item_category_name",nullable = false,length = 150)
    private String name;

    @NotBlank
    @Size(max = 150)
    @Column(name = "item_category_name_kann",nullable = false,length = 150)
    private String nameInKannada;

    @NotBlank
    @Size(max = 1)
    @Column(nullable = false,length = 1)
    private String status;

    @Size(max = 100)
    @Column(updatable = false, length = 100)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 100)
    @Column(length = 100)
    private String modifiedBy;

    @UpdateTimestamp
    private String modifiedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mas_store_group_id",referencedColumnName = "mas_store_group_id")
    private MasStoreItemGroup itemGroup;
}
