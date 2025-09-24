package com.erp.repo;

import com.erp.entity.MasTaluka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasTalukaRepo extends JpaRepository<MasTaluka,Long> {
    List<MasTaluka> findByStatusIgnoreCase(String status);
    List<MasTaluka> findByStatusInIgnoreCase(List<String> statuses);
}
