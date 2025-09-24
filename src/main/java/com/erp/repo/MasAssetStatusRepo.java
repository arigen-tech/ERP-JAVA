package com.erp.repo;


import com.erp.entity.MasAssetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasAssetStatusRepo extends JpaRepository<MasAssetStatus,Long> {

    List<MasAssetStatus> findByStatusIgnoreCase(String status);
    List<MasAssetStatus> findByStatusInIgnoreCase(List<String> statuses);
}
