package com.erp.repo;

import com.erp.entity.MasEmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasEmployeeStatusRepo extends JpaRepository<MasEmployeeStatus,Long> {

    List<MasEmployeeStatus> findByStatusIgnoreCase(String status);
    List<MasEmployeeStatus> findByStatusInIgnoreCase(List<String> statuses);
}
