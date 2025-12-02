package com.nov.accounts.service.impl;

import com.nov.accounts.dto.*;
import com.nov.accounts.entity.Accounts;
import com.nov.accounts.entity.Customer;
import com.nov.accounts.exception.ResourceNotFoundException;
import com.nov.accounts.mapper.AccountsMapper;
import com.nov.accounts.mapper.CustomerDetailsMapper;
import com.nov.accounts.mapper.CustomerMapper;
import com.nov.accounts.repository.AccountsRepository;
import com.nov.accounts.repository.CustomerRepository;
import com.nov.accounts.service.client.CardsFeignClient;
import com.nov.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService{

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * Fetches customer details based on the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return CustomerDetailsDto containing customer information
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer optionalCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomeId(optionalCustomer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "CustomerId", optionalCustomer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerDetailsMapper.toCustomerDetailsDto(optionalCustomer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.maptoAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCards(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
