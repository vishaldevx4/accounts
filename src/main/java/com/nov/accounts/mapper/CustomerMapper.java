package com.nov.accounts.mapper;

import com.nov.accounts.dto.CustomerDto;
import com.nov.accounts.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(Customer customer, CustomerDto customerDto) {
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDto toCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }
}
