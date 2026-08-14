package ru.razim.api.builders;

import ru.razim.api.models.SignupRequest;

public class SignupRequestBuilder {

    private String login;
    private String pass;

    public SignupRequestBuilder login(String login) {
        this.login = login;
        return this;
    }

    public SignupRequestBuilder pass(String pass) {
        this.pass = pass;
        return this;
    }

    public SignupRequest build() {
        return new SignupRequest(login, pass);
    }
}