package com.erp.repo;

import com.erp.entity.MasItemSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasItemSubcategoryRepo extends JpaRepository<MasItemSubcategory,Long> {

    List<MasItemSubcategory> findByStatusIgnoreCase(String status);
    List<MasItemSubcategory> findByStatusInIgnoreCase(List<String> statuses);


}
