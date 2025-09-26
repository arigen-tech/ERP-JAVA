package com.erp.repo;

import com.erp.entity.MasGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasGenderRepo extends JpaRepository<MasGender,Long> {
    List<MasGender> findByStatusIgnoreCase(String y);

    List<MasGender> findByStatusInIgnoreCase(List<String> y);
}
