package com.ivrs.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDetailsDTO {

   private String Month;

   private String Remittance;

   private String Basic;

   private String DA;

   private String MSP;

   private String NPA;
}
