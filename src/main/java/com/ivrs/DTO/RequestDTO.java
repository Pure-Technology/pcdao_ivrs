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

    public String getServiceType() {
        return serviceType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getdO2No() {
        return dO2No;
    }

    public String getdO2Year() {
        return dO2Year;
    }

    public String getCasualtyNo() {
        return casualtyNo;
    }
}
