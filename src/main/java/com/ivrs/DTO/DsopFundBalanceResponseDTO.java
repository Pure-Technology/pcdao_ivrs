package com.ivrs.DTO;


import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


public class DsopFundBalanceResponseDTO {

    private String dsopBalance;
    private String subscription;

    public String getDsopBalance() {
        return dsopBalance;
    }

    public void setDsopBalance(String dsopBalance) {
        this.dsopBalance = dsopBalance;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }
}
