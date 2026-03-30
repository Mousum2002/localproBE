package com.generation.localpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.localpro.model.OperationType;
import com.generation.localpro.model.OperationTypeByVendor;


public interface OperationTypeRepository extends JpaRepository<OperationType, Integer>
{
    List<OperationTypeByVendor> findByUserId(Integer userId);

    @Query("SELECT DISTINCT o FROM OperationType o LEFT JOIN o.tags t " +
       "WHERE LOWER(o.name) LIKE LOWER(CONCAT('%', :tag, '%')) " +
       "OR LOWER(t) LIKE LOWER(CONCAT('%', :tag, '%'))")
List<OperationType> findByOperationType(@Param("tag") String tag);

}
