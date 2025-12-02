package com.nov.accounts.mapper;

import com.nov.accounts.dto.CustomerDetailsDto;
import com.nov.accounts.dto.CustomerDto;
import com.nov.accounts.entity.Customer;

public class CustomerDetailsMapper {

    public static CustomerDetailsDto toCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setCustomerName(customer.getCustomerName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}
