package ru.razim.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Requirements {
    private Integer hardDrive;
    private String osName;
    private Integer ramGb;
    private String videoCard;
}
