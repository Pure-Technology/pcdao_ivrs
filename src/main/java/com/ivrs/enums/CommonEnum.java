package com.ivrs.enums;

import lombok.Getter;

@Getter
public enum CommonEnum {
    SALARY("Salary"),
    DSOP_FUND_BALANCE("DSOP Fund Balance"),TRANSPORTATION_CLAIMS("Transportation Claims"),
    LEDGER_CLAIMS(" Ledger claims"),DO2_DETAILS("DO 2 Details"),INCOME_TAX("Income Tax");

    private final String value;

    CommonEnum(String value) {
        this.value = value;
    }

}

