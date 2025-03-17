package com.ivrs.DTO;

import lombok.Data;

@Data
public class SessionRequestDTO {
    private String mobileNumber;
    private Long code;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
