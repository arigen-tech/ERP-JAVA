package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_qualification")
public class MasQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "qualification_code",nullable = false,length = 50)
    private String code;

    @Size(max = 100)
    @NotNull
    @Column(name = "qualification_name",nullable = false,length = 100)
    private String name;

    @Size(max = 1)
    @NotNull
    @Column(nullable = false, length = 1)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centre_id",referencedColumnName = "centre_id")
    private MasCentre centreId;//Replace CompanyId in MasCentre (Mas Hospital)

    @Size(max = 100)
    @Column(length = 100)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
