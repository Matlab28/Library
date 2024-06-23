package com.example.library.controller;

import com.example.library.dto.book.Book;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/random")
    public ResponseEntity<String> getRandomBook() {
        Book randomBook = bookService.getRandomBook();
        if (randomBook != null) {
            String formattedInfo = bookService.formatBookInfo(randomBook);
            return ResponseEntity.ok(formattedInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchBook(@RequestParam String searchText) {
        Book foundBook = bookService.searchBook(searchText);
        if (foundBook != null) {
            String formattedInfo = bookService.formatBookInfo(foundBook);
            return ResponseEntity.ok(formattedInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
