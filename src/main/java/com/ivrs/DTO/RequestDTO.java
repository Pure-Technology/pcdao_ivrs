package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestDTO {
    private String serviceType;
    private String customerNumber;
    private String month;
    private String year;
    private String date;
    private String dO2No; // for DO2 condition 2
    private String dO2Year; // for DO2 condition 2
    private String casualtyNo; // for DO2 condition 2
}
