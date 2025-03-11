package com.ivrs.service;

import com.ivrs.DTO.RequestDTO;

public interface IvrsService {
    Object getUserDetails(RequestDTO requestDTO);

    Object getCustomerDetails(RequestDTO requestDTO);
}
