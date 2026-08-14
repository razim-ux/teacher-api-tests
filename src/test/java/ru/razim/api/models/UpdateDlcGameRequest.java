package ru.razim.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateDlcGameRequest {
    private String description;
    private String dlcName;
    private Boolean isDlcFree;
    private Double price;
    private Integer rating;
    private SimilarDlc similarDlc;
}
