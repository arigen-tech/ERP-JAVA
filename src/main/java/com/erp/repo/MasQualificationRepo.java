package com.erp.repo;

import com.erp.entity.MasQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasQualificationRepo extends JpaRepository<MasQualification,Long> {

    List<MasQualification> findByStatusIgnoreCase(String status);
    List<MasQualification> findByStatusInIgnoreCase(List<String> statues);
}
