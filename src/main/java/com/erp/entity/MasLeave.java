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
@Table(name = "hr_mas_leave")
public class MasLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "description",unique = true,nullable = false,length = 250)
    private String description;

    @Size(max = 100)
    @Column(name = "updated_by",length = 100)
    private String updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id",referencedColumnName = "centre_id")
    private MasCentre centreId;

    @NotNull
    @Size(max = 1)
    @Column(name = "status",nullable = false,length = 1)
    private String status;

}
