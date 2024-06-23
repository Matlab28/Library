package com.example.library.service;

import com.example.library.client.LibraryClient;
import com.example.library.dto.book.Book;
import com.example.library.dto.book.BookResponse;
import com.example.library.dto.book.PublishedWorks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final LibraryClient bookClient;
    private final String host = "book-finder1.p.rapidapi.com";
    private final String key = "674678e122mshd00ec5b8f945302p1052bcjsn0ad69ed2af91";
    private final Random random = new Random();

    public BookResponse getBookData() {
        return bookClient.getData(host, key);
    }

    public String formatBookInfo(Book book) {
        String authors = book.getAuthors().stream()
                .collect(Collectors.joining(", "));

        String coverArtUrl = book.getPublishedWorks().stream()
                .filter(publishedWork -> publishedWork.getCoverArtUrl() != null)
                .map(PublishedWorks::getCoverArtUrl)
                .findFirst()
                .orElse("Cover art not available");

        return String.format("📚 Book information:\n\n"
                        + " - Book Title: %s\n\n"
                        + " - Book Author(s): %s\n\n"
                        + " - Book's Summary: %s\n\n"
                        + " - Book's Cover Art URL: %s\n",
                book.getTitle(),
                authors,
                book.getSummary(),
                coverArtUrl);
    }

    public Book getRandomBook() {
        BookResponse bookResponse = getBookData();
        if (bookResponse != null && !bookResponse.getResults().isEmpty()) {
            List<Book> books = bookResponse.getResults();
            return books.get(random.nextInt(books.size()));
        }
        return null;
    }

    public Book searchBook(String searchText) {
        BookResponse bookResponse = getBookData();
        if (bookResponse != null && !bookResponse.getResults().isEmpty()) {
            List<Book> books = bookResponse.getResults();
            return books.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                            book.getAuthors().stream().anyMatch(author -> author.toLowerCase().contains(searchText.toLowerCase())))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
