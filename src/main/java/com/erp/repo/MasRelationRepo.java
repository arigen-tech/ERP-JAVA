package com.erp.repo;

import com.erp.entity.MasRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasRelationRepo  extends JpaRepository<MasRelation,Long> {

    List<MasRelation> findByStatusIgnoreCase(String status);
    List<MasRelation> findByStatusInIgnoreCase(List<String> statuses);

}
