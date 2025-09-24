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
@Table(name = "mas_institute")
public class MasInstitute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institute_id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "institute_code", nullable = false,unique = true,length = 50)
    private String code;

    @NotNull
    @Size(max = 100)
    @Column(name = "institute_name", nullable = false,unique=false,length = 100)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(name = "status", nullable = false,length = 1)
    private String status;

    @Size(max = 300)
    @Column(name = "updated_by",length = 300)
    private String updatedBy;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
