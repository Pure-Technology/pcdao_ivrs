package com.example.ivrs.entities.primary;

import jakarta.persistence.*;

@Entity
@Table(name = "pcda_ivrs_register")
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srno")
    private Long srno;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "dob")
    private String dob;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "tpin")
    private Long tPin;

    @Column(name = "phone_no")
    private String mobileNumber;

    @Column(name = "language")
    private String language;

}
