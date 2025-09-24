package com.erp.repo;

import com.erp.entity.MasInstitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasInstituteRepo extends JpaRepository<MasInstitute,Long> {

    List<MasInstitute> findByStatusIgnoreCase(String status);
    List<MasInstitute> findByStatusInIgnoreCase(List<String> statuses);
}
