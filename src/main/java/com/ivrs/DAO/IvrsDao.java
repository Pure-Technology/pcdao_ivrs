package com.ivrs.DAO;

import com.ivrs.DTO.*;

public interface IvrsDao {
    DsopFundBalanceResponseDTO getDSOPFundDetails(String accNo , DsopFundBalanceResponseDTO responseDTO);
    DsopWithdrawalResponseDTO getDSOPWithdrawalDetails(String accNo , DsopWithdrawalResponseDTO responseDTO);
    TransportClaimsResponseDTO getTransportClaimsDetails(String accNo , TransportClaimsResponseDTO responseDTO);
    LedgerClaimsResponseDTO getLedgerClaimsDetails(String accNo ,LedgerClaimsResponseDTO responseDTO);
    DOIIResponseDTO getDoIIDetails(String accNo, DOIIResponseDTO responseDTO);
    IncomeTaxResponseDTO getIncomeTaxDetails(String accNo,IncomeTaxResponseDTO responseDTO);
}
