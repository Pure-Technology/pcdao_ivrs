package com.ivrs.DAO.impl;

import com.ivrs.DAO.IvrsDao;
import com.ivrs.DTO.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;

@Repository
@Slf4j
public class IvrsDaoImpl implements IvrsDao {

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory getSessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public DsopFundBalanceResponseDTO getDSOPFundDetails(String accNo, DsopFundBalanceResponseDTO responseDTO) {
        try (Session session = getSessionFactory().openSession()) {
            String closingBalanceQueryString = "SELECT closing_balance FROM fund_summary WHERE cda_no = :accNo";
            String subAmountQueryString = "SELECT sub_amount FROM fund_sub_refund WHERE cda_no = :accNo";

            Integer closingBalance = session.createQuery(closingBalanceQueryString, Integer.class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();
            Integer subscription = session.createQuery(subAmountQueryString, Integer.class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (closingBalance != null && subscription != null) {
                responseDTO.setDsopBalance(closingBalance.toString());
                responseDTO.setSubscription(subscription.toString());
            }
        } catch (Exception e) {
            log.error("Exception while getting DSOP fund details from database", e);
        }
        return responseDTO;
    }

    @Override
    public DsopWithdrawalResponseDTO getDSOPWithdrawalDetails(String accNo, DsopWithdrawalResponseDTO responseDTO) {
        try (Session session = getSessionFactory().openSession()) {
            String dsopWithdrawalQuery = "SELECT dp_sheet_date, amount_claimed, approval_amount, record_status FROM cbill_fund WHERE cdao_no = :accNo";

            Object[] result = session.createQuery(dsopWithdrawalQuery, Object[].class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (result != null) {
                responseDTO.setClaimedDate(String.valueOf(result[0]));
                responseDTO.setAmountClaimed(String.valueOf(result[1]));
                responseDTO.setAmountPassed(String.valueOf(result[2]));
                responseDTO.setStatus(String.valueOf(result[3]));
            }
        } catch (Exception e) {
            log.error("Exception while getting DSOP withdrawal details from database", e);
        }
        return responseDTO;
    }

    @Override
    public TransportClaimsResponseDTO getTransportClaimsDetails(String accNo, TransportClaimsResponseDTO responseDTO) {
        try (Session session = getSessionFactory().openSession()) {
            String query = "SELECT claim_date, amount_claimed, amount_passed, record_status FROM cbill_tada_ltc WHERE cdao_no = :accNo";

            Object[] result = session.createQuery(query, Object[].class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (result != null) {
                responseDTO.setClaimDate(String.valueOf(result[0]));
                responseDTO.setAmountClaimed(String.valueOf(result[1]));
                responseDTO.setAmountPassed(String.valueOf(result[2]));
                responseDTO.setStatus(isRecordStatusD() ? "D" : String.valueOf(result[3]));
            }
        } catch (Exception e) {
            log.error("Exception while getting transport claims details from database", e);
        }
        return responseDTO;
    }

    private boolean isRecordStatusD() {
        try (Session session = getSessionFactory().openSession()) {
            String statusQuery = "SELECT COUNT(*) FROM dak_d WHERE record_status = 'D'";
            Long count = session.createQuery(statusQuery, Long.class).uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            log.error("Exception while checking record status", e);
            return false;
        }
    }

    @Override
    public LedgerClaimsResponseDTO getLedgerClaimsDetails(String accNo, LedgerClaimsResponseDTO responseDTO) {
        try (Session session = getSessionFactory().openSession()) {
            String query = "SELECT ref_date, amount_claimed, amount_passed, record_status FROM cbill_medical WHERE cdao_no = :accNo";

            Object[] result = session.createQuery(query, Object[].class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (result != null) {
                responseDTO.setClaimDate(String.valueOf(result[0]));
                responseDTO.setAmountClaimed(String.valueOf(result[1]));
                responseDTO.setAmountPassed(String.valueOf(result[2]));
                responseDTO.setStatus(String.valueOf(result[3]));
            }
        } catch (Exception e) {
            log.error("Exception while getting ledger claims details from database", e);
        }
        return responseDTO;
    }

    @Override
    public DOIIResponseDTO getDoIIDetails(String accNo, DOIIResponseDTO responseDTO) {
        try (Session session = getSessionFactory().openSession()) {
            String do2Query = "SELECT d.do2_item_no, d.from_date, d.to_date, d.status, d.reason FROM do2 d WHERE d.acc_no = :accNo";

            Object[] do2Result = session.createQuery(do2Query, Object[].class)
                    .setParameter("accNo", accNo)
                    .uniqueResult();

            if (do2Result != null) {
                responseDTO.setCasualty(String.valueOf(do2Result[0]));
                responseDTO.setFromDate(String.valueOf(do2Result[1]));
                responseDTO.setToDate(String.valueOf(do2Result[2]));
                responseDTO.setStatus(String.valueOf(do2Result[3]));
                responseDTO.setReason(String.valueOf(do2Result[4]));
            }

            String arrearQuery = "SELECT a.amount FROM arrear a WHERE a.cdao_no = :cdaoNo";
            Integer amountPassed = session.createQuery(arrearQuery, Integer.class)
                    .setParameter("cdaoNo", accNo)
                    .uniqueResult();

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
        try (Session session = getSessionFactory().openSession()) {
            String itRecoveryQuery = "SELECT i.it_recovery_amount FROM it_recovery i WHERE i.cdao_no = :cdaoNo";
            BigDecimal itRecoveryAmount = session.createQuery(itRecoveryQuery, BigDecimal.class)
                    .setParameter("cdaoNo", cdaoNo)
                    .uniqueResult();
            if (itRecoveryAmount != null) {
                responseDTO.setItRecovery(itRecoveryAmount.toString());
            }

            String taxAssessmentQuery = "SELECT t.total_tax_deducted FROM tax_assessment t WHERE t.cdao_no = :cdaoNo";
            BigDecimal totalTaxDeducted = session.createQuery(taxAssessmentQuery, BigDecimal.class)
                    .setParameter("cdaoNo", cdaoNo)
                    .uniqueResult();
            if (totalTaxDeducted != null) {
                responseDTO.setAccumulated(totalTaxDeducted.toString());
            }
        } catch (Exception e) {
            log.error("Exception while getting Income Tax details from database", e);
        }
        return responseDTO;
    }
}
