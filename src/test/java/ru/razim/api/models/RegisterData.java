package ru.razim.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterData {

    private Integer id;
    private String login;
    private String pass;
    private List<Object> games;

}