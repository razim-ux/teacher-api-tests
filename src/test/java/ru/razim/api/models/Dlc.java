package ru.razim.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dlc {
    private String description;
    private String dlcName;
    private Boolean isDlcFree;
    private Double price;
    private Integer rating;
    private SimilarDlc similarDlc;
}
