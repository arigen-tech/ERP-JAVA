package com.erp.repo;


import com.erp.entity.MasCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasCapacityRepo extends JpaRepository<MasCapacity,Long> {

    List<MasCapacity> findByStatusIgnoreCase(String status);
    List<MasCapacity> findByStatusInIgnoreCase(List<String> statuses);
}
