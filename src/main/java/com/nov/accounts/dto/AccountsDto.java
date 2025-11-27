package com.nov.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AccountsDto {

    private String accountType;
    private Long accountNumber;
    private String branchAddress;
}
