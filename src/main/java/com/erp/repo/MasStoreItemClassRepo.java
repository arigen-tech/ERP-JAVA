package com.erp.repo;

import com.erp.entity.MasStoreItemClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasStoreItemClassRepo extends JpaRepository<MasStoreItemClass,Long> {
    List<MasStoreItemClass> findByStatusIgnoreCase(String y);

    List<MasStoreItemClass> findByStatusInIgnoreCase(List<String> y);
}
