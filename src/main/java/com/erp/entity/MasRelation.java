package com.erp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "mas_relation ")
public class MasRelation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 20)
    @Column(name = "status_code",nullable = false,length =20)
    private String statusCode;

    @Size(max =50)
    @Column(name = "name",nullable = false,length = 50)
    private String statusName;

    @Size(max = 1)
    @Column(name = "status",nullable = false,length = 1)
    private String status;

    @UpdateTimestamp
    @Column(name = "last_cng_date")
    private LocalDateTime updatedAt;

    @Size(max = 255)
    @Column(name = "last_cng_by",length = 255)
    private String updatedBy;


}
