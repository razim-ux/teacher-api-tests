package ru.razim.api.clients;

import ru.razim.api.models.*;
import ru.razim.api.specs.ResponseSpec;

public class AuthApiClient extends BaseApiClient {
    private static final String SIGNUP_ENDPOINT = "/api/signup";
    private static final String LOGIN_ENDPOINT = "/api/login";

    public SignupResponse signup(SignupRequest body) {
        return post(
                SIGNUP_ENDPOINT,
                body,
                ResponseSpec.created201(),
                SignupResponse.class
        );
    }

    public ApiResponse signupWithBadRequest(SignupRequest body) {
        return post(
                SIGNUP_ENDPOINT,
                body,
                ResponseSpec.badRequest400(),
                ApiResponse.class
        );
    }

    public LoginResponse login(LoginRequest body) {
        return post(
                LOGIN_ENDPOINT,
                body,
                ResponseSpec.success200(),
                LoginResponse.class
        );
    }

    public ApiResponse loginWithBadRequest(LoginRequest body){
        return  post(
                LOGIN_ENDPOINT,
                body,
                ResponseSpec.badRequest400(),
                ApiResponse.class
        );
    }

    public void loginWithUnauthorized(LoginRequest body) {
        post(
                LOGIN_ENDPOINT,
                body,
                ResponseSpec.unauthorized401(),
                Object.class
        );
    }
}
