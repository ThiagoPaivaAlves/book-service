package com.microservices.book_service.client;

import com.microservices.book_service.model.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name="currency-service")
public interface CurrencyClient {
    
    @GetMapping(value = "/currency/{amount}/{from}/{to}")
    CurrencyDto getCurrency(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from,
                                   @PathVariable("to") String to);
}
