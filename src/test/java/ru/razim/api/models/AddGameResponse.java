package ru.razim.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddGameResponse {

    @JsonProperty("register_data")
    private GameResponse registerData;

    private Info info;
}
