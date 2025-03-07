package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsopWithdrawalResponseDTO {
    private String claimedDate;
    private String amountClaimed;
    private String amountPassed;
    private String status;
}
