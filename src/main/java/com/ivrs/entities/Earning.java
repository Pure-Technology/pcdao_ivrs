package com.ivrs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "earning")
public class Earning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_employee", nullable = false)
    private Long fkEmployee;

    @Column(name = "fk_pay_code", nullable = false)
    private Integer fkPayCode;

    @Column(nullable = false)
    private Integer amount;

    private Integer rate;

    @Column(name = "month_ending", nullable = false)
    private String monthEnding;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "created_at", columnDefinition = "timestamp with time zone default now()")
    private LocalDateTime createdAt;

    @Column(name = "round_off_amount", nullable = false)
    private Boolean roundOffAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFkEmployee() {
        return fkEmployee;
    }

    public void setFkEmployee(Long fkEmployee) {
        this.fkEmployee = fkEmployee;
    }

    public Integer getFkPayCode() {
        return fkPayCode;
    }

    public void setFkPayCode(Integer fkPayCode) {
        this.fkPayCode = fkPayCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getMonthEnding() {
        return monthEnding;
    }

    public void setMonthEnding(String monthEnding) {
        this.monthEnding = monthEnding;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRoundOffAmount() {
        return roundOffAmount;
    }

    public void setRoundOffAmount(Boolean roundOffAmount) {
        this.roundOffAmount = roundOffAmount;
    }
}
