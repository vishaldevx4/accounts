package com.nov.accounts.service.impl;

import com.nov.accounts.constants.AccountsConstants;
import com.nov.accounts.constants.CustomerConstants;
import com.nov.accounts.dto.AccountsDto;
import com.nov.accounts.dto.CustomerDto;
import com.nov.accounts.entity.Accounts;
import com.nov.accounts.entity.Customer;
import com.nov.accounts.exception.CustomerAlreadyExistsException;
import com.nov.accounts.exception.ResourceNotFoundException;
import com.nov.accounts.mapper.AccountsMapper;
import com.nov.accounts.mapper.CustomerMapper;
import com.nov.accounts.repository.AccountsRepository;
import com.nov.accounts.repository.CustomerRepository;
import com.nov.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(new Customer(), customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(String.valueOf(customerDto.getMobileNumber()));
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(CustomerConstants.CUSTOMER_ALREDADY_REGISTERED_FOR_MOBILE_NUMBER + String.valueOf(customerDto.getMobileNumber()));
        }


        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }

    public Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomeId(customer.getCustomerId());
        long randomNumber = 1_000_000_0000L + new Random().nextInt(90_000_0000);
        accounts.setAccountNumber(randomNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto getCustomerByMobileNumber(String mobileNumber) {
        Customer optionalCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomeId(optionalCustomer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "CustomerId", optionalCustomer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.toCustomerDto(optionalCustomer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.maptoAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();


        //first get the accounts:

        if(accountsDto !=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "AccountNumber", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accounts, accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomeId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId.toString()));

            CustomerMapper.toCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;

        }
        return isUpdated;
    }
}
