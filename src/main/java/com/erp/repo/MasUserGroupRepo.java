package com.erp.repo;

import com.erp.entity.MasUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasUserGroupRepo extends JpaRepository<MasUserGroup,Long> {
    List<MasUserGroup> findByStatusIgnoreCase(String y);

    List<MasUserGroup> findByStatusInIgnoreCase(List<String> y);
}
