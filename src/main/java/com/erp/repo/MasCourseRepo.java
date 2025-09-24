package com.erp.repo;

import com.erp.entity.MasCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasCourseRepo extends JpaRepository<MasCourse,Long> {

    List<MasCourse> findByStatusIgnoreCase(String status);
    List<MasCourse> findByStatusInIgnoreCase(List<String> statuses);
}
