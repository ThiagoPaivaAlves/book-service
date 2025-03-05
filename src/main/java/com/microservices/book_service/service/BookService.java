package com.microservices.book_service.service;

import com.microservices.book_service.client.CurrencyClient;
import com.microservices.book_service.model.BookDto;
import com.microservices.book_service.model.CurrencyDto;
import com.microservices.book_service.repository.BookRepository;
import com.microservices.book_service.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final Environment environment;
//    private final CurrencyClientConfig clientConfig;
    private final BookRepository repository;
    private final CurrencyClient currencyClient;
    
    public BookDto findBook(Long id, String currency) {
        //this should be a 404 I'll leave like this for now
        BookDto bookDto = Mapper.bookEntityMapper(repository.findById(id).orElseThrow(RuntimeException::new));
        
        
        HashMap<String, String> params = new HashMap<>();
        params.put("amount", bookDto.getPrice().toString());
        params.put("to", currency);
        params.put("from", "USD");
        
        //using rest template
//        ResponseEntity<CurrencyDto> responseRT = new RestTemplate().getForEntity(
//                clientConfig.getCurrencyServiceUrl() + "/currency/{amount" + "}/{from}/{to}", CurrencyDto.class,
//                params);
        
        //using Feign
        CurrencyDto response = currencyClient.getCurrency(bookDto.getPrice(), "USD", currency );
        
        //using rest template
//        if (response.getStatusCode() == HttpStatusCode.valueOf(200) ) {
//            CurrencyDto currencyDto = response.getBody();
//            assert currencyDto != null;
//            bookDto.setPrice(currencyDto.getConvertedValue());
//        }
        bookDto.setEnvironment("Book port: " + environment.getProperty("local.server.port") + " Currency port:" +
                                       response.getEnvironment());
        bookDto.setPrice(response.getConvertedValue());
        return bookDto;
    }
}
