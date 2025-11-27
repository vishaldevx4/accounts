package com.nov.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity{

    @Column(name="customer_id")
    private long customeId;

    @Column(nullable = false)
    private String accountType;

    @Id
    @Column(nullable = false)
    private long accountNumber;

    @Column(nullable = false)
    private String branchAddress;



}
