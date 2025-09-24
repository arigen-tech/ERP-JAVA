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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mas_religion")
public class MasReligion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "religion_id")
    private Long id;

    @Size(max = 10)
    @Column(name = "religion_code",unique = true,nullable = false,length = 10)
    private String code;

    @Size(max = 255)
    @Column(name = "religion_name",unique = true,nullable = false,length = 255)
    private String name;

    @Size(max = 1)
    @Column(name = "status",nullable = false,length = 1)
    private String status;

    @Size(max = 255)
    @Column(length = 255)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
