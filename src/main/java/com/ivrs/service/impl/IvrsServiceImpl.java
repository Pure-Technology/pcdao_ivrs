package com.ivrs.service.impl;

import com.ivrs.DTO.RequestDTO;
import com.ivrs.DTO.SalaryDetailsDTO;
import com.ivrs.entities.Earning;
import com.ivrs.entities.Employee;
import com.ivrs.entities.PaySummary;
import com.ivrs.entities.UserAuth;
import com.ivrs.globalException.CustomerNotFoundException;
import com.ivrs.repositories.EarningRepository;
import com.ivrs.repositories.EmployeeRepository;
import com.ivrs.repositories.PaySummeryRepository;
import com.ivrs.repositories.UserAuthRepository;
import com.ivrs.DAO.IvrsDao;
import com.ivrs.DTO.*;
import com.ivrs.commonUtility.StringUtility;
import com.ivrs.enums.CommonEnum;
import com.ivrs.repositories.UserAuthRepository;
import com.ivrs.service.IvrsService;
import jdk.dynalink.linker.LinkerServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IvrsServiceImpl implements IvrsService {
    private final UserAuthRepository userAuthRepository;
    private final IvrsDao ivrsDao;

    public IvrsServiceImpl(UserAuthRepository userAuthRepository, IvrsDao ivrsDao) {
        this.userAuthRepository = userAuthRepository;
        this.ivrsDao = ivrsDao;
    }

    private static final Logger logger = LoggerFactory.getLogger(IvrsServiceImpl.class);

    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    PaySummeryRepository paySummeryRepository;

    @Autowired
    EarningRepository earningRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Object getUserDetails(RequestDTO requestDTO) {
        logger.info("Request received for customer number: {}", requestDTO.getCustomerNumber()); // Log the request

        Optional<UserAuth> userAuth = this.userAuthRepository.findByMobileNumber(requestDTO.getCustomerNumber());

        if (userAuth.isPresent()) {
            logger.info("Customer found for mobile number: {}", requestDTO.getCustomerNumber()); // Log when customer is found
            return userAuth;
        } else {
            logger.warn("Customer not found for mobile number: {}", requestDTO.getCustomerNumber()); // Log when no customer is found
            throw new CustomerNotFoundException("Customer not found for mobile number: " + requestDTO.getCustomerNumber()); // Throw custom exception
        }
    @Override
    public Object getCustomerDetails(RequestDTO requestDTO) {
        String serviceType = "";
        String customerNumber = "";
        String month = "";
        String year = "";
        String date = "";
        String cdaAccNo = "";
        try {
            Object responseDTO = null;
            serviceType = requestDTO.getServiceType();
            customerNumber = requestDTO.getCustomerNumber();
            month = requestDTO.getMonth();
            year = requestDTO.getYear();
            date = requestDTO.getDate();
            if (!StringUtility.isNullOrEmptyString(customerNumber)) {
                cdaAccNo = userAuthRepository.getCdaAccNumberBasedOnMobileNum(customerNumber);
                if (!StringUtility.isNullOrEmptyString(cdaAccNo)) {
                    CommonEnum serviceEnum = getServiceEnumFromString(serviceType);
                    switch (serviceEnum) {
                        case SALARY:
                            break;
                        case DSOP_FUND_BALANCE:
                            responseDTO = getDsopFundBalanceDetails(cdaAccNo);
                            break;
                        case DSOP_WITHDRAWAL:
                            responseDTO = getDSOPWithdrawalDetails(cdaAccNo);
                            break;
                        case TRANSPORTATION_CLAIMS:
                            responseDTO = getTransportationClaimDetails(cdaAccNo);
                            break;
                        case LEDGER_CLAIMS:
                            responseDTO = getLedgerClaimDetails(cdaAccNo);
                            break;
                        case DO2_DETAILS:
                            responseDTO = getDo2Details(cdaAccNo);
                            break;
                        case INCOME_TAX:
                            responseDTO = getIncomeTaxDetails(cdaAccNo);
                            break;
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CDA Account Not Found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile Number is Not Register, Kindly contact to PCDA office to Register your mobile number");
            }
        } catch (Exception e) {
            log.error("Exception while getting customer details for service : ".concat(requestDTO.getServiceType()), e);
        }
        return null;
    }

    private Object getIncomeTaxDetails(String cdaAccNo) {
        IncomeTaxResponseDTO responseDTO = new IncomeTaxResponseDTO();
        try {
            responseDTO = ivrsDao.getIncomeTaxDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting Income Tax details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }

    private Object getDo2Details(String cdaAccNo) {
        DOIIResponseDTO responseDTO = new DOIIResponseDTO();
        try {
            responseDTO = ivrsDao.getDoIIDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting DO II details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }

    private Object getLedgerClaimDetails(String cdaAccNo) {
        LedgerClaimsResponseDTO responseDTO = new LedgerClaimsResponseDTO();
        try {
            responseDTO = ivrsDao.getLedgerClaimsDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting Ledger Claims details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }

    private Object getTransportationClaimDetails(String cdaAccNo) {
        TransportClaimsResponseDTO responseDTO = new TransportClaimsResponseDTO();
        try {
            responseDTO = ivrsDao.getTransportClaimsDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting Transportation Claims details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }

    private Object getDsopFundBalanceDetails(String cdaAccNo) {
        DsopFundBalanceResponseDTO responseDTO = new DsopFundBalanceResponseDTO();
        try {
            responseDTO = ivrsDao.getDSOPFundDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting dsop fund balance details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }

    private Object getDSOPWithdrawalDetails(String cdaAccNo) {
        DsopWithdrawalResponseDTO responseDTO = new DsopWithdrawalResponseDTO();
        try {
            responseDTO = ivrsDao.getDSOPWithdrawalDetails(cdaAccNo, responseDTO);
        } catch (Exception e) {
            log.error("Exception while getting dsop fund balance details for cda account no : ".concat(cdaAccNo), e);
        }
        return responseDTO;
    }
    private CommonEnum getServiceEnumFromString(String serviceType) {
        for (CommonEnum serviceEnum : CommonEnum.values()) {
            if (serviceEnum.getValue().equalsIgnoreCase(serviceType)) {
                return serviceEnum;
            }
        }
        return null;
    }

    private Object getSalaryDetails(RequestDTO requestDTO) {
        UserAuth userAuth = (UserAuth) getUserDetails(requestDTO);
        String cdacNo = userAuth.getAccountNumber();
        SalaryDetailsDTO salaryDetailsDTO = new SalaryDetailsDTO();

        // Fetch PaySummary where status is "C"
        List<PaySummary> paySummary = paySummeryRepository.findByCdaoNoAndRecordStatus(cdacNo, "C");
        if (!paySummary.isEmpty() && paySummary.get(0) != null) {
            salaryDetailsDTO.setMonth(paySummary.get(0).getMonthEnding());
            salaryDetailsDTO.setRemittance(String.valueOf(paySummary.get(0).getEcsAmount()));
        }

        // Fetch latest earnings
        getLatestEarning(cdacNo, 1).ifPresent(earning -> salaryDetailsDTO.setBasic(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 3).ifPresent(earning -> salaryDetailsDTO.setDA(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 4).ifPresent(earning -> salaryDetailsDTO.setMSP(earning.getAmount().toString()));
        getLatestEarning(cdacNo, 6).ifPresent(earning -> salaryDetailsDTO.setNPA(earning.getAmount().toString()));

        return salaryDetailsDTO;
    }


    public Optional<Earning> getLatestEarning(String cdacNo, Integer fkPayCode) {
        Optional<Employee> employee = employeeRepository.findByCdaoNo(cdacNo);
        if (employee.isPresent()) {
            List<Earning> earnings = earningRepository.findByFkEmployeeAndFkPayCode(employee.get().getId(), fkPayCode);

            return earnings.stream()
                    .max(Comparator.comparing(Earning::getId));
        }
        return Optional.empty();
    }


}



