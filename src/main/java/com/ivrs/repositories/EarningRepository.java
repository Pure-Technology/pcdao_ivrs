package com.ivrs.repositories;

import com.ivrs.entities.Earning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EarningRepository extends JpaRepository<Earning, Long> {

    List<Earning> findByFkEmployee(Long fkEmployee);

    List<Earning> findByFkEmployeeAndFkPayCode(@Param("fkEmployee") Long fkEmployee, @Param("fkPayCode") Integer fkPayCode);

}
