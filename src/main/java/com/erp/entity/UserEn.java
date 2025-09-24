//package com.erp.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Collection;
//
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="users")
//public class UserEn implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Size(max =12)
//    @Column(name = "adhar_number",length = 12)
//    private String aadharNumber;
//
//    @Column(name = "address_info")
//    private String addressInfo;
//
//    @CreationTimestamp
//    @Column(name = "created_at",updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "created_by")
//    private String createdBy;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "updated_by")
//    private String updatedBy;
//
//    @Column(name = "date_of_birth")
//    private LocalDate dob;
//
//    @Column(name = "email")
//    private String username;
//
//    @Column(name = "current_password")
//    private String curPwd;
//
//    @Column(name = "old_password")
//    private String oldPwd;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "middle_name")
//    private String middleName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column(name="phone_number")
//    private String phnNumber;
//
//    @Column(name = "is_verified")
//    private Boolean isVerified;
//
//    @Column(name = "last_cng_by")
//    private String lastCngBy;
//
//    @Column(name = "last_cng_date")
//    private Instant lastCngDate;
//
//    @Column(name = "nationality")
//    private String nationality;
//
//    @Column(name = "pin_code")
//    private String pinCode;
//
//    @Column(name = "profile_pic")
//    private String profilePic;
//
//    @Column(name = "verification_method")
//    private String verificationMethod;
//
//    @Column(name = "pan_number")
//    private String panNumber;
//
//    @Column(name = "passport_number")
//    private String passportNumber;
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.curPwd;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
