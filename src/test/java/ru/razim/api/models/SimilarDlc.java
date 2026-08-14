package ru.razim.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimilarDlc {
    private String dlcNameFromAnotherGame;
    private Boolean isFree;
}
