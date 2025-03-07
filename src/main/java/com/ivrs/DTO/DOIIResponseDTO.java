package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DOIIResponseDTO {

    private String casualty;
    private String fromDate;
    private String toDate;
    private String status;
    private String reason;
    private String amountPassed;
}
