package com.erp.repo;

import com.erp.entity.MasVendorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasVendorTypeRepo extends JpaRepository<MasVendorType,Long> {

    List<MasVendorType> findByStatusIgnoreCase(String status);
    List<MasVendorType> findByStatusInIgnoreCase(List<String> statuses);
}
