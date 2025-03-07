package com.ivrs.DAO.impl;

import com.ivrs.DAO.IvrsDao;
import com.ivrs.DTO.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;

@Repository
@Slf4j
public class IvrsDaoImpl implements IvrsDao {

    @Autowired
    @Qualifier(value = "falconDataSource")
    private SessionFactory sessionFactory;


    @Override
    public DsopFundBalanceResponseDTO getDSOPFundDetails(String accNo, DsopFundBalanceResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String closingBalanceQueryString = "SELECT closing_balance FROM fund_summary WHERE cda_no = :accNo";
            String subAmountQueryString = "SELECT sub_amount FROM fund_sub_refund WHERE cda_no = :accNo";
            Query<Integer> dsopBalanceQuery = session.createQuery(closingBalanceQueryString, Integer.class);
            Query<Integer> subscriptionQuery = session.createQuery(subAmountQueryString, Integer.class);
            dsopBalanceQuery.setParameter("accNo", accNo);
            subscriptionQuery.setParameter("accNo", accNo);
            Integer closingBalance = dsopBalanceQuery.uniqueResult();
            Integer subscription = subscriptionQuery.uniqueResult();
            if (closingBalance != null && subscription != null) {
                responseDTO.setDsopBalance(closingBalance.toString());
                responseDTO.setSubscription(subscription.toString());
            }
        } catch (Exception e) {
            log.error("Exception while getting dsop fund details from database", e);
        }
        return responseDTO;
    }

    @Override
    public DsopWithdrawalResponseDTO getDSOPWithdrawalDetails(String accNo, DsopWithdrawalResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String dsopWithdrawalQuery = "SELECT dp_sheet_date, amount_claimed, approval_amount, record_status FROM cbill_fund WHERE cdao_no = :accNo";
            Query<Object[]> dsopBalanceQuery = session.createQuery(dsopWithdrawalQuery);
            dsopBalanceQuery.setParameter("accNo", accNo);
            Object[] result = dsopBalanceQuery.uniqueResult();
            if (result != null) {
                String dpSheetDate = (String) result[0];
                String amountClaimed = (String) result[1];
                String approvalAmount = (String) result[2];
                String recordStatus = (String) result[3];
                responseDTO.setClaimedDate(dpSheetDate);
                responseDTO.setAmountClaimed(amountClaimed);
                responseDTO.setAmountPassed(approvalAmount);
                responseDTO.setStatus(recordStatus);
            }
        } catch (Exception e) {
            log.error("Exception while getting dsop withdrawal details from database", e);
        }
        return responseDTO;
    }

    @Override
    public TransportClaimsResponseDTO getTransportClaimsDetails(String accNo, TransportClaimsResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String query = "SELECT claim_date, amount_claimed, amount_passed, record_status FROM cbill_tada_ltc WHERE cdao_no = :accNo";
            Query<Object[]> transportClaimsQuery = session.createQuery(query);
            transportClaimsQuery.setParameter("accNo", accNo);
            Object[] result = transportClaimsQuery.uniqueResult();

            if (result != null) {
                String claimDate = (String) result[0];
                String amountClaimed = (String) result[1];
                String amountPassed = (String) result[2];
                String recordStatus = (String) result[3];

                responseDTO.setClaimDate(claimDate);
                responseDTO.setAmountClaimed(amountClaimed);
                responseDTO.setAmountPassed(amountPassed);
                if (isRecordStatusD(recordStatus)) {
                    responseDTO.setStatus("D");
                } else {
                    responseDTO.setStatus(recordStatus);
                }
            }
        } catch (Exception e) {
            log.error("Exception while getting transport claims details from database", e);
        }
        return responseDTO;
    }

    private boolean isRecordStatusD(String recordStatus) {
        Session session = this.sessionFactory.getCurrentSession();
        //this table is requird dak_d
        String statusQuery = "SELECT COUNT(*) FROM dak_d WHERE record_status = :recordStatus";
        Query<Long> query = session.createQuery(statusQuery, Long.class);
        query.setParameter("recordStatus", "D");
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }


    @Override
    public LedgerClaimsResponseDTO getLedgerClaimsDetails(String accNo, LedgerClaimsResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String query = "SELECT ref_date, amount_claimed, amount_passed, record_status FROM cbill_medical WHERE cdao_no = :accNo";
            Query<Object[]> ledgerClaimsQuery = session.createQuery(query);
            ledgerClaimsQuery.setParameter("accNo", accNo);
            Object[] result = ledgerClaimsQuery.uniqueResult();

            if (result != null) {
                String claimDate = (String) result[0];
                String amountClaimed = (String) result[1];
                String amountPassed = (String) result[2];
                String recordStatus = (String) result[3];

                responseDTO.setClaimDate(claimDate);
                responseDTO.setAmountClaimed(amountClaimed);
                responseDTO.setAmountPassed(amountPassed);
                responseDTO.setStatus(recordStatus);
            }
        } catch (Exception e) {
            log.error("Exception while getting ledger claims details from database", e);
        }
        return responseDTO;
    }


    @Override
    public DOIIResponseDTO getDoIIDetails(String accNo, DOIIResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String do2Query = "SELECT d.do2_item_no, d.from_date, d.to_date, d.status, d.reason, d.cdao_no " +
                    "FROM do2 d WHERE d.acc_no = :accNo";
            Query<Object[]> query1 = session.createQuery(do2Query, Object[].class);
            query1.setParameter("accNo", accNo);
            Object[] do2Result = query1.uniqueResult();

            if (do2Result != null) {
                responseDTO.setCasualty((String) do2Result[0]);
                responseDTO.setFromDate((String) do2Result[1]);
                responseDTO.setToDate((String) do2Result[2]);
                responseDTO.setStatus((String) do2Result[3]);
                responseDTO.setReason((String) do2Result[4]);
            }
            String arrearQuery = "SELECT a.amount FROM arrear a WHERE a.cdao_no = :cdaoNo";
            Query<Integer> query2 = session.createQuery(arrearQuery, Integer.class);
            query2.setParameter("cdaoNo", accNo);
            Integer amountPassed = query2.uniqueResult();

            if (amountPassed != null) {
                responseDTO.setAmountPassed(String.valueOf(amountPassed));
            }
        } catch (Exception e) {
            log.error("Exception while getting DO II details from database", e);
        }
        return responseDTO;
    }

    @Override
    public IncomeTaxResponseDTO getIncomeTaxDetails(String cdaoNo, IncomeTaxResponseDTO responseDTO) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            String itRecoveryQuery = "SELECT i.it_recovery_amount FROM it_recovery i WHERE i.cdao_no = :cdaoNo";
            Query<BigDecimal> query1 = session.createQuery(itRecoveryQuery, BigDecimal.class);
            query1.setParameter("cdaoNo", cdaoNo);
            BigDecimal itRecoveryAmount = query1.uniqueResult();
            if (itRecoveryAmount != null) {
                responseDTO.setItRecovery(itRecoveryAmount.toString());
            }
            String taxAssessmentQuery = "SELECT t.total_tax_deducted FROM tax_assessment t WHERE t.cdao_no = :cdaoNo";
            Query<BigDecimal> query2 = session.createQuery(taxAssessmentQuery, BigDecimal.class);
            query2.setParameter("cdaoNo", cdaoNo);
            BigDecimal totalTaxDeducted = query2.uniqueResult();
            if (totalTaxDeducted != null) {
                responseDTO.setAccumulated(totalTaxDeducted.toString());
            }
        } catch (Exception e) {
            log.error("Exception while getting Income Tax details from database", e);
        }
        return responseDTO;
    }


}
