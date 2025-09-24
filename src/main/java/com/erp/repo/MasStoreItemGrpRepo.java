package com.erp.repo;


import com.erp.entity.MasStoreItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasStoreItemGrpRepo extends JpaRepository<MasStoreItemGroup,Long> {

    List<MasStoreItemGroup> findByStatusIgnoreCase(String status);
    List<MasStoreItemGroup> findByStatusInIgnoreCase(List<String> statuses);

}
