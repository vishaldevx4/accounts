package com.nov.accounts.service;

import com.nov.accounts.dto.CustomerDto;
import org.springframework.http.ResponseEntity;

public interface IAccountService {

    /**
     *
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);
    CustomerDto getCustomerByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

}
