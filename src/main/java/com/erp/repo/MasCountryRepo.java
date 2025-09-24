package com.erp.repo;

import com.erp.entity.MasCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MasCountryRepo extends JpaRepository<MasCountry,Long> {

    List<MasCountry> findByStatusIgnoreCase(String status);
    List<MasCountry> findByStatusInIgnoreCase(List<String> statuses);
}
