package com.ivrs.repositories;

import com.ivrs.entities.PaySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaySummeryRepository extends JpaRepository<PaySummary, Long> {

    List<PaySummary> findByCdaoNoAndRecordStatus(@Param("cdaoNo") String cdaoNo, @Param("recordStatus") String recordStatus);


}
