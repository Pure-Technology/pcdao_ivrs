package com.ivrs.service.impl;

import com.ivrs.DAO.IvrsDao;
import com.ivrs.DTO.*;
import com.ivrs.commonUtility.StringUtility;
import com.ivrs.enums.CommonEnum;
import com.ivrs.repositories.UserAuthRepository;
import com.ivrs.service.IvrsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IvrsServiceImpl implements IvrsService {
    private final UserAuthRepository userAuthRepository;
    private final IvrsDao ivrsDao;

    public IvrsServiceImpl(UserAuthRepository userAuthRepository, IvrsDao ivrsDao) {
        this.userAuthRepository = userAuthRepository;
        this.ivrsDao = ivrsDao;
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
}
