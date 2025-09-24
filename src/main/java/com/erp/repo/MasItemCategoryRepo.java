package com.erp.repo;


import com.erp.entity.MasCourse;
import com.erp.entity.MasItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasItemCategoryRepo extends JpaRepository<MasItemCategory,Long> {

    List<MasItemCategory> findByStatusIgnoreCase(String status);
    List<MasItemCategory> findByStatusInIgnoreCase(List<String> statuses);
}
