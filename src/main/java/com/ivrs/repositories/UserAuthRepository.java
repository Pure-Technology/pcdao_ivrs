package com.ivrs.repositories;

import com.ivrs.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    @Query("Select accountNumber from UserAuth where accountNumber= :customerNumber")
    String getCdaAccNumberBasedOnMobileNum(@Param("customerNumber") String customerNumber);
}
