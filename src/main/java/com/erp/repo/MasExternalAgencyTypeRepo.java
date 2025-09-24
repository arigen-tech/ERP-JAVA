package com.erp.repo;

import com.erp.entity.MasExternalAgencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasExternalAgencyTypeRepo extends JpaRepository<MasExternalAgencyType,Long> {

    List<MasExternalAgencyType> findByStatusIgnoreCase(String status);
    List<MasExternalAgencyType> findByStatusInIgnoreCase(List<String> statuses);
}
