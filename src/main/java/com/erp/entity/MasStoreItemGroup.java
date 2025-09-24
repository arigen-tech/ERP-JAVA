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
@Table(name = "mas_store_group")
public class MasStoreItemGroup {//Group MAster in App or mas_store_group in DB

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mas_store_group_id")
    private Long itemGrpId;

    @NotNull
    @Size(max = 20)
    @Column(name = "mas_store_group_code",length = 20,nullable = false)
    private String itemGrpCode;

    @NotNull
    @Size(max = 50)
    @Column(name = "mas_store_group_name",length = 50,nullable = false)
    private String itemGrpName;

    @NotNull
    @Size(max = 1)
    @Column(length = 1,nullable = false)
    private String status;

    @Size(max = 20)
    @Column(length = 50,updatable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Size(max = 20)
    @Column(length = 50)
    private String modifiedBy;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
