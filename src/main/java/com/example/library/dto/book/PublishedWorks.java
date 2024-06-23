package com.example.library.dto.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishedWorks {
    private String isbn;
    private String binding;
    @JsonProperty("cover_art_url")
    private String coverArtUrl;
    @JsonProperty("english_language_learner")
    private Boolean englishLanguageLearner;
    private Integer copyright;
    @JsonProperty("published_work_id")
    private Integer publishedWorkId;
    @JsonProperty("page_count")
    private Integer pageCount;
}
