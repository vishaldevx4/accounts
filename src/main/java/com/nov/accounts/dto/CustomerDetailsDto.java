package com.nov.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer, Accounts, Cards and Loans information"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the Customer", example = "John Doe"
    )
    @NotEmpty(message = "Customer Name can not be a null or empty")
    @Size(min=5, max = 50, message = "Customer Name can not be more than 50 characters")
    private String customerName;

    @Schema(description = "Email of the Customer")
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email can not be a null or empty")
    private String email;
    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Size(min=10, max = 10, message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Accounts information of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(description = "Loans information of the Customer")
    private LoansDto loansDto;

    @Schema(description = "Cards information of the Customer")
    private CardsDto cardsDto;
}
