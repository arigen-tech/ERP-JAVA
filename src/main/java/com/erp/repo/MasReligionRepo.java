package com.erp.repo;

import com.erp.entity.MasReligion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasReligionRepo extends JpaRepository<MasReligion,Long> {

    List<MasReligion> findByStatusIgnoreCase(String status);
    List<MasReligion> findByStatusInIgnoreCase(List<String> statues);
}
