package com.nov.accounts.service.client;

import com.nov.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/cards/getCards",consumes = "application/json")
    ResponseEntity<CardsDto> fetchCards(@RequestParam String mobileNumber);
}
