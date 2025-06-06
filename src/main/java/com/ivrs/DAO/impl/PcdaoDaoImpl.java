package com.ivrs.DAO.impl;

import com.ivrs.DAO.PcdaoDao;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class PcdaoDaoImpl implements PcdaoDao {

    @Autowired
    @Qualifier("pcdaoEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory getSessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public String getCdaAccNo(String mobNo) {
        String cdaAccNo = "";
        try (Session session = getSessionFactory().openSession()) {
            String cdaAccNoGetQuery = "SELECT account_number FROM pcda_ivrs_register  WHERE phone_no = :customerNumber";
             cdaAccNo = (String) session.createNativeQuery(cdaAccNoGetQuery, String.class)
                    .setParameter("customerNumber", mobNo)
                    .uniqueResult();
        } catch (Exception e) {
//            log.error("Exception while getting DSOP fund details from database", e);
            e.printStackTrace();
        }
        return cdaAccNo;
    }

    @Override
    public boolean validateUser(String phoneNo, Long tPin) {
        boolean exists = false;
        try (Session session = getSessionFactory().openSession()) {
            String query = "SELECT EXISTS (SELECT 1 FROM pcda_ivrs_register WHERE phone_no = :phoneNo AND tpin = :tPin)";
            exists = (boolean) session.createNativeQuery(query)
                    .setParameter("phoneNo", phoneNo)
                    .setParameter("tPin", tPin)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

}
