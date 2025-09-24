package com.erp.repo;


import com.erp.entity.MasCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasCurrencyRepo extends JpaRepository<MasCurrency,Long> {

    List<MasCurrency> findByStatusIgnoreCase(String status);
    List<MasCurrency> findByStatusInIgnoreCase(List<String> statuses);
}
