package com.erp.repo;

import com.erp.entity.MasLeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasLeaveTypeRepo extends JpaRepository<MasLeaveType ,Long>{

    List<MasLeaveType> findByStatusIgnoreCase(String status);
    List<MasLeaveType> findByStatusInIgnoreCase(List<String> statuses);
}
