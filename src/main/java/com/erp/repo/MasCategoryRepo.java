package com.erp.repo;

import com.erp.entity.MasCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasCategoryRepo extends JpaRepository<MasCategory,Long> {

    List<MasCategory> findByStatusIgnoreCase(String status);
    List<MasCategory> findByStatusInIgnoreCase(List<String> statuses);
}
