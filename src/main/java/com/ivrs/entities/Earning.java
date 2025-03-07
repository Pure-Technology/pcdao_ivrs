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


}
