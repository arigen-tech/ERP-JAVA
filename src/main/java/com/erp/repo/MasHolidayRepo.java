package com.erp.repo;

import com.erp.entity.MasHoliday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasHolidayRepo extends JpaRepository<MasHoliday,Long> {

    List<MasHoliday> findByStatusIgnoreCase(String status);
    List<MasHoliday> findByStatusInIgnoreCase(List<String> statues);
}
