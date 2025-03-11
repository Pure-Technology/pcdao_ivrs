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

   public String getMonth() {
      return Month;
   }

   public void setMonth(String month) {
      Month = month;
   }

   public String getRemittance() {
      return Remittance;
   }

   public void setRemittance(String remittance) {
      Remittance = remittance;
   }

   public String getBasic() {
      return Basic;
   }

   public void setBasic(String basic) {
      Basic = basic;
   }

   public String getDA() {
      return DA;
   }

   public void setDA(String DA) {
      this.DA = DA;
   }

   public String getMSP() {
      return MSP;
   }

   public void setMSP(String MSP) {
      this.MSP = MSP;
   }

   public String getNPA() {
      return NPA;
   }

   public void setNPA(String NPA) {
      this.NPA = NPA;
   }
}
