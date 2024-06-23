package com.example.library.client;

import com.example.library.dto.book.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bookApi", url = "https://book-finder1.p.rapidapi.com/api/search?series=Wings%20of%20fire&book_type=Fiction&lexile_min=600&lexile_max=800&results_per_page=25&page=1")
public interface LibraryClient {
    @GetMapping
    BookResponse getData(@RequestHeader("x-rapidapi-host") String host,
                         @RequestHeader("x-rapidapi-key") String apiKey);
}
