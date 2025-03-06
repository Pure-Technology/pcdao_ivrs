package com.ivrs.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsopFundBalanceResponseDTO {

    private String dsopBalance;
    private String subscription;
}
