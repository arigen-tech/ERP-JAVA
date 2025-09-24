package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "mas_marital_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasMaritalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "status_code",nullable = false,length = 30)
    private String statusCode;

    @Size(max =30)
    @Column(name = "name",length = 30,nullable = false)
    private String name;

    @Size(max = 1)
    @NotNull
    @Column(name = "status",nullable = false,length = 1)
    private String status;

    @Size(max = 200)
    @Column(name = "last_cng_by",length = 200)
    private String lastCngBy;

    @Column(name = "last_cng_date")
    @UpdateTimestamp
    private LocalDateTime lastCngDate;
}
