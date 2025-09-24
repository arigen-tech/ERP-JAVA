package com.erp.repo;

import com.erp.entity.MasLeave;
import com.erp.entity.MasRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasLeaveRepo extends JpaRepository<MasLeave,Long> {
    List<MasLeave> findByStatusIgnoreCase(String status);
    List<MasLeave> findByStatusInIgnoreCase(List<String> statuses);

}
