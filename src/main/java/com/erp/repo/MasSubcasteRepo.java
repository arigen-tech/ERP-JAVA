package com.erp.repo;

import com.erp.entity.MasSubcaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasSubcasteRepo extends JpaRepository<MasSubcaste,Long> {
    List<MasSubcaste> findByStatusIgnoreCase(String status);
    List<MasSubcaste> findByStatusInIgnoreCase(List<String> statuses);
}
