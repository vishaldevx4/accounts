package com.nov.accounts.service.impl;

import com.nov.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    /**
     * Fetches customer details based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return CustomerDetailsDto containing customer information
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
