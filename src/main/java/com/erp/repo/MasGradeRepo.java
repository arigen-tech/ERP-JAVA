package com.erp.repo;

import com.erp.entity.MasGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MasGradeRepo extends JpaRepository<MasGrade,Long> {

    List<MasGrade> findByStatusIgnoreCase(String status);
    List<MasGrade> findByStatusInIgnoreCase(List<String> statuses);
}
