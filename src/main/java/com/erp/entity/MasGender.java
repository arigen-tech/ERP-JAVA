package com.erp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="mas_gender")
public class MasGender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "group_name", length = 50, nullable = false)
    private String genderName;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "gender_code",length=1)
    private String genderCode;


    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "update_on")
    private LocalDateTime updatedOn;

}
