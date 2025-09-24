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
@Table(name = "mas_country")
public class MasCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "country_code",length = 10,nullable = false)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "country_name",nullable = false,length = 100)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(length = 1,nullable = false)
    private String status;

    @Size(max = 50)
    @Column(updatable = false, length = 50)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private String createdAt;

    @Size(max = 50)
    @Column(length = 50)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id",referencedColumnName = "currency_id",nullable = false)
    private MasCurrency currency;
}
