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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mas_title")
public class MasTitle {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "title_code",nullable = false,length = 10)
    private String code;

    @NotNull
    @Size(max = 50)
    @Column(name = "title_name",nullable = false,length = 50)
    private String name;

    @NotNull
    @Size(max = 1)
    @Column(nullable = false, length = 1)
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
    private String updatedAt;
}
