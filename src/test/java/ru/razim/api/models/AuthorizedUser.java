package ru.razim.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizedUser {

    private String token;
    private Integer id;
    private String login;
    private String password;
}