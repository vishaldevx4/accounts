package com.nov.accounts.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerDto {

    private String customerName;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;

}
