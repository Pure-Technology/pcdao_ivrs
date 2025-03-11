package com.ivrs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pay_summary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySummary {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_dak")
    private Long fkDak;

    @Column(name = "fk_employee", nullable = false)
    private Long fkEmployee;

    @Column(name = "cdao_no", nullable = false, length = 8)
    private String cdaoNo;

    @Column(name = "check_digit", nullable = false, length = 1)
    private String checkDigit;

    @Column(name = "fk_employee_category")
    private Integer fkEmployeeCategory;

    @Column(name = "month_ending", nullable = false, length = 7)
    private String monthEnding;

    @Column(name = "gross_pay", nullable = false)
    private Integer grossPay;

    @Column(name = "fund")
    private Integer fund;

    @Column(name = "court_attachment")
    private Integer courtAttachment;

    @Column(name = "pli")
    private Integer pli;

    @Column(name = "agis")
    private Integer agis;

    @Column(name = "loans")
    private Integer loans;

    @Column(name = "debit_adjustment")
    private Integer debitAdjustment;

    @Column(name = "income_tax")
    private Integer incomeTax;

    @Column(name = "total_debits")
    private Integer totalDebits;

    @Column(name = "net_pay", nullable = false)
    private Integer netPay;

    @Column(name = "month_pay", nullable = false)
    private Integer monthPay;

    @Column(name = "opening_balance")
    private Integer openingBalance;

    @Column(name = "closing_balance")
    private Integer closingBalance;

    @Column(name = "fk_usr")
    private Integer fkUsr;

    @Column(name = "employee_type", length = 1)
    private String employeeType;

    @Column(name = "ne_paid_amount")
    private Integer nePaidAmount;

    @Column(name = "rank", length = 10)
    private String rank;

    @Column(name = "pay_level", length = 3)
    private String payLevel;

    @Column(name = "ifsc", length = 11)
    private String ifsc;

    @Column(name = "bank_account_no", length = 70)
    private String bankAccountNo;

    @Column(name = "unit_code", length = 14)
    private String unitCode;

    @Column(name = "record_status", length = 1)
    private String recordStatus;

    @Column(name = "created_at", columnDefinition = "timestamp with time zone default now()")
    private LocalDateTime createdAt;

    @Column(name = "closed", nullable = false)
    private Boolean closed;

    @Column(name = "transfer_in_amount")
    private Integer transferInAmount;

    @Column(name = "credit_adjustment")
    private Integer creditAdjustment;

    @Column(name = "fama")
    private Integer fama;

    @Column(name = "total_credits")
    private Integer totalCredits;

    @Column(name = "reason", columnDefinition = "text")
    private String reason;

    @Column(name = "remarks", columnDefinition = "text")
    private String remarks;

    @Column(name = "ecs_amount")
    private Integer ecsAmount;

    @Column(name = "withhold_amt")
    private Integer withholdAmt;

    @Column(name = "ba_id")
    private Long baId;

    @Column(name = "nps_gc")
    private Integer npsGc;

    @Column(name = "nps_ec")
    private Integer npsEc;

    @Column(name = "perm_ob")
    private Integer permOb;

    @Column(name = "perm_cb")
    private Integer permCb;

    @Column(name = "cr_bal_amount")
    private Integer crBalAmount;

    @Column(name = "cr_bal_date")
    private LocalDate crBalDate;

    @Column(name = "cr_bal_status", length = 1)
    private String crBalStatus;

    @Column(name = "cr_bal_reason", columnDefinition = "text")
    private String crBalReason;

    @Column(name = "cr_bal_aud_date")
    private LocalDate crBalAudDate;

    @Column(name = "cr_bal_aao_date")
    private LocalDate crBalAaoDate;

    @Column(name = "cr_bal_ao_date")
    private LocalDate crBalAoDate;

    @Column(name = "cr_bal_aud")
    private Integer crBalAud;

    @Column(name = "cr_bal_aao")
    private Integer crBalAao;

    @Column(name = "cr_bal_ao")
    private Integer crBalAo;

    @Column(name = "cr_bal_dakid", length = 25)
    private String crBalDakid;

    @Column(name = "cgeis")
    private Integer cgeis;

    @Column(name = "tagif")
    private Integer tagif;

    @Column(name = "agif_flpay")
    private Integer agifFlpay;

    @Column(name = "dsop_flpay")
    private Integer dsopFlpay;

    @Column(name = "afmso")
    private Integer afmso;

    @Column(name = "batch")
    private Integer batch;

    @Column(name = "mps_batch")
    private Integer mpsBatch;

    @Column(name = "perm_cr_bal_amount")
    private Integer permCrBalAmount;

    @Column(name = "total_cr_bal_amount")
    private Integer totalCrBalAmount;

    @Column(name = "exp_flag", length = 14)
    private String expFlag;

    @Column(name = "corps_code", length = 5)
    private String corpsCode;

    @Column(name = "encash_withheld")
    private Integer encashWithheld;

    @Column(name = "encash_total")
    private Integer encashTotal;

    @Column(name = "encash_paid")
    private Integer encashPaid;

    @Column(name = "crbal_debit_me", length = 7)
    private String crbalDebitMe;

    @Column(name = "encash_released")
    private Integer encashReleased;

    @Column(name = "encash_release_me", length = 7)
    private String encashReleaseMe;

    @Column(name = "fs_date")
    private LocalDate fsDate;

    @Column(name = "task")
    private Integer task;

    @Column(name = "section", length = 20)
    private String section;

    @Column(name = "rank_level")
    private Integer rankLevel;

    @Column(name = "rank_code")
    private Integer rankCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkDak() {
        return fkDak;
    }

    public void setFkDak(Long fkDak) {
        this.fkDak = fkDak;
    }

    public Long getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(Long fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(String checkDigit) {
        this.checkDigit = checkDigit;
    }

    public String getCdaoNo() {
        return cdaoNo;
    }

    public void setCdaoNo(String cdaoNo) {
        this.cdaoNo = cdaoNo;
    }

    public Integer getFkEmployeeCategory() {
        return fkEmployeeCategory;
    }

    public void setFkEmployeeCategory(Integer fkEmployeeCategory) {
        this.fkEmployeeCategory = fkEmployeeCategory;
    }

    public String getMonthEnding() {
        return monthEnding;
    }

    public void setMonthEnding(String monthEnding) {
        this.monthEnding = monthEnding;
    }

    public Integer getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Integer grossPay) {
        this.grossPay = grossPay;
    }

    public Integer getFund() {
        return fund;
    }

    public void setFund(Integer fund) {
        this.fund = fund;
    }

    public Integer getCourtAttachment() {
        return courtAttachment;
    }

    public void setCourtAttachment(Integer courtAttachment) {
        this.courtAttachment = courtAttachment;
    }

    public Integer getPli() {
        return pli;
    }

    public void setPli(Integer pli) {
        this.pli = pli;
    }

    public Integer getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Integer incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Integer getDebitAdjustment() {
        return debitAdjustment;
    }

    public void setDebitAdjustment(Integer debitAdjustment) {
        this.debitAdjustment = debitAdjustment;
    }

    public Integer getAgis() {
        return agis;
    }

    public void setAgis(Integer agis) {
        this.agis = agis;
    }

    public Integer getLoans() {
        return loans;
    }

    public void setLoans(Integer loans) {
        this.loans = loans;
    }

    public Integer getTotalDebits() {
        return totalDebits;
    }

    public void setTotalDebits(Integer totalDebits) {
        this.totalDebits = totalDebits;
    }

    public Integer getRankCode() {
        return rankCode;
    }

    public void setRankCode(Integer rankCode) {
        this.rankCode = rankCode;
    }

    public Integer getRankLevel() {
        return rankLevel;
    }

    public void setRankLevel(Integer rankLevel) {
        this.rankLevel = rankLevel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public LocalDate getFsDate() {
        return fsDate;
    }

    public void setFsDate(LocalDate fsDate) {
        this.fsDate = fsDate;
    }

    public String getEncashReleaseMe() {
        return encashReleaseMe;
    }

    public void setEncashReleaseMe(String encashReleaseMe) {
        this.encashReleaseMe = encashReleaseMe;
    }

    public Integer getEncashReleased() {
        return encashReleased;
    }

    public void setEncashReleased(Integer encashReleased) {
        this.encashReleased = encashReleased;
    }

    public String getCrbalDebitMe() {
        return crbalDebitMe;
    }

    public void setCrbalDebitMe(String crbalDebitMe) {
        this.crbalDebitMe = crbalDebitMe;
    }

    public Integer getEncashPaid() {
        return encashPaid;
    }

    public void setEncashPaid(Integer encashPaid) {
        this.encashPaid = encashPaid;
    }

    public Integer getEncashTotal() {
        return encashTotal;
    }

    public void setEncashTotal(Integer encashTotal) {
        this.encashTotal = encashTotal;
    }

    public Integer getEncashWithheld() {
        return encashWithheld;
    }

    public void setEncashWithheld(Integer encashWithheld) {
        this.encashWithheld = encashWithheld;
    }

    public String getCorpsCode() {
        return corpsCode;
    }

    public void setCorpsCode(String corpsCode) {
        this.corpsCode = corpsCode;
    }

    public String getExpFlag() {
        return expFlag;
    }

    public void setExpFlag(String expFlag) {
        this.expFlag = expFlag;
    }

    public Integer getTotalCrBalAmount() {
        return totalCrBalAmount;
    }

    public void setTotalCrBalAmount(Integer totalCrBalAmount) {
        this.totalCrBalAmount = totalCrBalAmount;
    }

    public Integer getPermCrBalAmount() {
        return permCrBalAmount;
    }

    public void setPermCrBalAmount(Integer permCrBalAmount) {
        this.permCrBalAmount = permCrBalAmount;
    }

    public Integer getMpsBatch() {
        return mpsBatch;
    }

    public void setMpsBatch(Integer mpsBatch) {
        this.mpsBatch = mpsBatch;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getAfmso() {
        return afmso;
    }

    public void setAfmso(Integer afmso) {
        this.afmso = afmso;
    }

    public Integer getAgifFlpay() {
        return agifFlpay;
    }

    public void setAgifFlpay(Integer agifFlpay) {
        this.agifFlpay = agifFlpay;
    }

    public Integer getDsopFlpay() {
        return dsopFlpay;
    }

    public void setDsopFlpay(Integer dsopFlpay) {
        this.dsopFlpay = dsopFlpay;
    }

    public Integer getTagif() {
        return tagif;
    }

    public void setTagif(Integer tagif) {
        this.tagif = tagif;
    }

    public Integer getCgeis() {
        return cgeis;
    }

    public void setCgeis(Integer cgeis) {
        this.cgeis = cgeis;
    }

    public String getCrBalDakid() {
        return crBalDakid;
    }

    public void setCrBalDakid(String crBalDakid) {
        this.crBalDakid = crBalDakid;
    }

    public Integer getCrBalAo() {
        return crBalAo;
    }

    public void setCrBalAo(Integer crBalAo) {
        this.crBalAo = crBalAo;
    }

    public Integer getCrBalAao() {
        return crBalAao;
    }

    public void setCrBalAao(Integer crBalAao) {
        this.crBalAao = crBalAao;
    }

    public Integer getCrBalAud() {
        return crBalAud;
    }

    public void setCrBalAud(Integer crBalAud) {
        this.crBalAud = crBalAud;
    }

    public LocalDate getCrBalAoDate() {
        return crBalAoDate;
    }

    public void setCrBalAoDate(LocalDate crBalAoDate) {
        this.crBalAoDate = crBalAoDate;
    }

    public LocalDate getCrBalAaoDate() {
        return crBalAaoDate;
    }

    public void setCrBalAaoDate(LocalDate crBalAaoDate) {
        this.crBalAaoDate = crBalAaoDate;
    }

    public String getCrBalReason() {
        return crBalReason;
    }

    public void setCrBalReason(String crBalReason) {
        this.crBalReason = crBalReason;
    }

    public LocalDate getCrBalAudDate() {
        return crBalAudDate;
    }

    public void setCrBalAudDate(LocalDate crBalAudDate) {
        this.crBalAudDate = crBalAudDate;
    }

    public String getCrBalStatus() {
        return crBalStatus;
    }

    public void setCrBalStatus(String crBalStatus) {
        this.crBalStatus = crBalStatus;
    }

    public LocalDate getCrBalDate() {
        return crBalDate;
    }

    public void setCrBalDate(LocalDate crBalDate) {
        this.crBalDate = crBalDate;
    }

    public Integer getCrBalAmount() {
        return crBalAmount;
    }

    public void setCrBalAmount(Integer crBalAmount) {
        this.crBalAmount = crBalAmount;
    }

    public Integer getPermCb() {
        return permCb;
    }

    public void setPermCb(Integer permCb) {
        this.permCb = permCb;
    }

    public Integer getPermOb() {
        return permOb;
    }

    public void setPermOb(Integer permOb) {
        this.permOb = permOb;
    }

    public Integer getNpsEc() {
        return npsEc;
    }

    public void setNpsEc(Integer npsEc) {
        this.npsEc = npsEc;
    }

    public Integer getNpsGc() {
        return npsGc;
    }

    public void setNpsGc(Integer npsGc) {
        this.npsGc = npsGc;
    }

    public Integer getWithholdAmt() {
        return withholdAmt;
    }

    public void setWithholdAmt(Integer withholdAmt) {
        this.withholdAmt = withholdAmt;
    }

    public Long getBaId() {
        return baId;
    }

    public void setBaId(Long baId) {
        this.baId = baId;
    }

    public Integer getEcsAmount() {
        return ecsAmount;
    }

    public void setEcsAmount(Integer ecsAmount) {
        this.ecsAmount = ecsAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Integer getFama() {
        return fama;
    }

    public void setFama(Integer fama) {
        this.fama = fama;
    }

    public Integer getCreditAdjustment() {
        return creditAdjustment;
    }

    public void setCreditAdjustment(Integer creditAdjustment) {
        this.creditAdjustment = creditAdjustment;
    }

    public Integer getTransferInAmount() {
        return transferInAmount;
    }

    public void setTransferInAmount(Integer transferInAmount) {
        this.transferInAmount = transferInAmount;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getPayLevel() {
        return payLevel;
    }

    public void setPayLevel(String payLevel) {
        this.payLevel = payLevel;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getNePaidAmount() {
        return nePaidAmount;
    }

    public void setNePaidAmount(Integer nePaidAmount) {
        this.nePaidAmount = nePaidAmount;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Integer getFkUsr() {
        return fkUsr;
    }

    public void setFkUsr(Integer fkUsr) {
        this.fkUsr = fkUsr;
    }

    public Integer getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Integer closingBalance) {
        this.closingBalance = closingBalance;
    }

    public Integer getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(Integer monthPay) {
        this.monthPay = monthPay;
    }

    public Integer getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Integer openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Integer getNetPay() {
        return netPay;
    }

    public void setNetPay(Integer netPay) {
        this.netPay = netPay;
    }
}
