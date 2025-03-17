package com.ivrs.DTO;

public class DOIIRequestDTO {

    private String customerNumber;
    private String serviceType;
    private String dO2No;
    private String dO2Year;
    private Long casualtyNo;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getdO2No() {
        return dO2No;
    }

    public void setdO2No(String dO2No) {
        this.dO2No = dO2No;
    }

    public String getdO2Year() {
        return dO2Year;
    }

    public void setdO2Year(String dO2Year) {
        this.dO2Year = dO2Year;
    }

    public Long getCasualtyNo() {
        return casualtyNo;
    }

    public void setCasualtyNo(Long casualtyNo) {
        this.casualtyNo = casualtyNo;
    }
}
