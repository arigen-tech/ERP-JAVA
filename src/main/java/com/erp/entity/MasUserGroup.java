package com.erp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="mas_user_group")
public class MasUserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name", length = 50, nullable = false)
    private String groupName;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "update_on")
    private LocalDateTime updatedOn;

}
