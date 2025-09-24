package com.erp.repo;

import com.erp.entity.MasState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasStateRepo extends JpaRepository<MasState,Long> {

    List<MasState> findByStatusIgnoreCase(String status);
    List<MasState> findByStatusInIgnoreCase(List<String> statuses);
}
