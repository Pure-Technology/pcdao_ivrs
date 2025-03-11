package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeTaxResponseDTO {

    private String itRecovery;
    private String accumulated;

    public String getItRecovery() {
        return itRecovery;
    }

    public void setItRecovery(String itRecovery) {
        this.itRecovery = itRecovery;
    }

    public String getAccumulated() {
        return accumulated;
    }

    public void setAccumulated(String accumulated) {
        this.accumulated = accumulated;
    }
}
