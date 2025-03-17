package com.ivrs.DTO;

import lombok.Data;

@Data
public class SessionRequestDTO {
    private String mobileNumber;
    private Long code;
}
