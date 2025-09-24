package com.erp.repo;


import com.erp.entity.MasVillage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasVillageRepo extends JpaRepository<MasVillage,Long> {
    List<MasVillage> findByStatusIgnoreCase(String status);
    List<MasVillage> findByStatusInIgnoreCase(List<String> statuses);
}
