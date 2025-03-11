package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DOIIResponseDTO {

    private String casualty;
    private String fromDate;
    private String toDate;
    private String status;
    private String reason;
    private String amountPassed;

    public String getCasualty() {
        return casualty;
    }

    public void setCasualty(String casualty) {
        this.casualty = casualty;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmountPassed() {
        return amountPassed;
    }

    public void setAmountPassed(String amountPassed) {
        this.amountPassed = amountPassed;
    }
}
