package com.nov.accounts.controller;

import com.nov.accounts.constants.AccountsConstants;
import com.nov.accounts.dto.CustomerDto;
import com.nov.accounts.dto.ResponseDto;
import com.nov.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Accounts Service!";
    }

    private IAccountService iAccountService;

    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/customer/{mobileNumber}")
    public ResponseEntity<CustomerDto> getCustomerByMobileNumber(@PathVariable String mobileNumber) {
        CustomerDto customer = iAccountService.getCustomerByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);

    }




}
