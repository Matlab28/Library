package com.example.library.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private String title;
    private List<String> authors;
    private String summary;
    @JsonProperty("covert_art_url")
    private String covertArtUrl;
    @JsonProperty("published_works")
    private List<PublishedWorks> publishedWorks;
}
