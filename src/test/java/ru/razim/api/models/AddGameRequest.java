package ru.razim.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddGameRequest {

    private String company;
    private String description;
    private Dlc[] dlcs;
    private Long gameId;
    private String genre;
    private Boolean isFree;
    private Double price;

    @JsonProperty("publish_date")
    private String publishDate;

    private Integer rating;
    private Boolean requiredAge;
    private Requirements requirements;
    private String[] tags;
    private String title;
}