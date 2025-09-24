package com.erp.repo;

import com.erp.entity.MasDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasDistrictRepo extends JpaRepository<MasDistrict,Long> {

    List<MasDistrict> findByStatusIgnoreCase(String status);
    List<MasDistrict> findByStatusInIgnoreCase(List<String> statuses);
}
