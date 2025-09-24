package com.erp.repo;

import com.erp.entity.MasCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasCentreRepo extends JpaRepository<MasCentre,Long> {

    List<MasCentre> findByStatusIgnoreCase(String status);
    List<MasCentre> findByStatusInIgnoreCase(List<String> statuses);
}
