package com.erp.repo;

import com.erp.entity.MasTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasTitleRepo extends JpaRepository<MasTitle,Long> {
    List<MasTitle> findByStatusIgnoreCase(String status);
    List<MasTitle> findByStatusInIgnoreCase(List<String> statuses);
}
