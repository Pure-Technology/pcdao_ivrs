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
}
