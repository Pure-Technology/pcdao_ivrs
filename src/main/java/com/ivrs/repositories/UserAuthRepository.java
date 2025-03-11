package com.ivrs.repositories;

import com.ivrs.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(transactionManager = "pcdaoTransactionManager")
@EnableJpaRepositories(
       basePackages = "com.ivrs.repositories",
        entityManagerFactoryRef = "pcdaoEntityManagerFactory",
        transactionManagerRef = "pcdaoTransactionManager"
)
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    @Query("SELECT u.accountNumber FROM UserAuth u WHERE u.mobileNumber = :customerNumber")
    String getCdaAccNumberBasedOnMobileNum(@Param("customerNumber") String customerNumber);

    Optional<UserAuth> findByMobileNumber(String mobileNumber);
}
