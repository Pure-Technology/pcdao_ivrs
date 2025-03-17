package com.ivrs.DAO;

public interface PcdaoDao {

    String getCdaAccNo(String mobNo);
    boolean validateUser(String mobNo , Long tPin);
}
