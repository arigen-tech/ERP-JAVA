package com.erp.repo;

import com.erp.entity.MasMaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasMaritalStatusRepo extends JpaRepository<MasMaritalStatus,Long> {

    List<MasMaritalStatus> findByStatusIgnoreCase(String status);
    List<MasMaritalStatus> findByStatusInIgnoreCase(List<String> statuses);

}
