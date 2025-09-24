package com.erp.repo;


import com.erp.entity.MasAssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasAssetCategoryRepo extends JpaRepository<MasAssetCategory,Long> {

    List<MasAssetCategory> findByStatusIgnoreCase(String status);
    List<MasAssetCategory> findByStatusInIgnoreCase(List<String> statuses);
}
