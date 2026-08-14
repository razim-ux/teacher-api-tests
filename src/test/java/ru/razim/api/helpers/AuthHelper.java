package ru.razim.api.helpers;

import ru.razim.api.clients.AuthApiClient;
import ru.razim.api.models.*;
import ru.razim.api.testdata.TestDataFactory;

public class AuthHelper {
    private static final AuthApiClient authApiClient = new AuthApiClient();

    public static String getJwtToken() {
        return createAuthorizedUser().getToken();
    }

    public static AuthorizedUser createAuthorizedUser() {
        SignupRequest signupBody = TestDataFactory.validSignupRequest();

        String login = signupBody.getLogin();
        String password = signupBody.getPass();

        SignupResponse signupResponse = authApiClient.signup(signupBody);

        LoginRequest loginBody = new LoginRequest(login, password);

        LoginResponse loginResponse = authApiClient.login(loginBody);

        return new AuthorizedUser(
                loginResponse.getToken(),
                signupResponse.getRegisterData().getId(),
                login,
                password
        );

    }
}