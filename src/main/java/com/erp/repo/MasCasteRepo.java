package com.erp.repo;

import com.erp.entity.MasCaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MasCasteRepo extends JpaRepository<MasCaste,Long> {

    List<MasCaste> findByStatusIgnoreCase(String status);
    List<MasCaste> findByStatusInIgnoreCase(List<String> statuses);
}
