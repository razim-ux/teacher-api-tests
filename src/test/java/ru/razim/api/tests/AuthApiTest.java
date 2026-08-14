package ru.razim.api.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.razim.api.assertions.ApiAssertions;
import ru.razim.api.constants.ApiMessages;
import ru.razim.api.helpers.TestDataGenerator;
import ru.razim.api.models.*;
import ru.razim.api.steps.ApiSteps;
import ru.razim.api.testdata.TestDataFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AuthApiTest extends BaseTest {

    private final ApiSteps apiSteps = new ApiSteps();

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Проверка успешной регистрации нового пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void signupNewUserTest() {

        String login = TestDataGenerator.generateLogin();
        String password = TestDataGenerator.generatePassword();

        SignupRequest body = new SignupRequest(login, password);

        SignupResponse response = apiSteps.signup(body);

        assertThat(response.getRegisterData().getLogin(), equalTo(login));
        assertThat(response.getRegisterData().getId(), notNullValue());
        assertThat(response.getInfo().getStatus(), equalTo(ApiMessages.SUCCESS));
    }

    @Test
    @DisplayName("Регистрация с пустым логином")
    @Description("Проверка ошибки регистрации без логина")
    @Severity(SeverityLevel.NORMAL)
    public void signupWithEmptyLoginTest() {

        String login = "";
        String password = TestDataGenerator.generatePassword();

        SignupRequest body = new SignupRequest(login, password);

        ApiResponse response = apiSteps.signupBadRequest(body);

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.LOGIN_ALREADY_EXIST
        );
    }

    @Test
    @DisplayName("Регистрация без пароля")
    @Description("Проверка ошибки регистрации без пароля")
    @Severity(SeverityLevel.NORMAL)
    public void signupWithoutPasswordTest() {

        SignupRequest request = TestDataFactory.signupWithoutPassword();

        ApiResponse response = apiSteps.signupBadRequest(request);

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.MISSING_LOGIN_OR_PASSWORD
        );
    }

    @Test
    @DisplayName("Регистрация без логина")
    @Description("Проверка ошибки регистрации без логина")
    @Severity(SeverityLevel.NORMAL)
    public void signupWithoutLoginTest() {

        SignupRequest request = TestDataFactory.signupWithoutLogin();

        ApiResponse response = apiSteps.signupBadRequest(request);

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.MISSING_LOGIN_OR_PASSWORD
        );
    }

    @Test
    @DisplayName("Получение JWT токена")
    @Description("Проверка успешного получения JWT после авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void getJwtTokenTest() {

        String login = TestDataGenerator.generateLogin();
        String password = TestDataGenerator.generatePassword();

        SignupRequest signupBody = new SignupRequest(login, password);

        apiSteps.signup(signupBody);

        LoginRequest loginBody = new LoginRequest(login, password);

        LoginResponse response = apiSteps.login(loginBody);

        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @DisplayName("Получение текущего пользователя после авторизации")
    @Description("Проверка получения информации о текущем пользователе")
    @Severity(SeverityLevel.CRITICAL)
    public void getCurrentUserAfterLoginTest() {

        String login = TestDataGenerator.generateLogin();
        String password = TestDataGenerator.generatePassword();

        SignupRequest signupBody = new SignupRequest(login, password);

        apiSteps.signup(signupBody);

        LoginRequest loginBody = new LoginRequest(login, password);

        LoginResponse loginResponse = apiSteps.login(loginBody);

        UserResponse userResponse =
                apiSteps.getCurrentUser(loginResponse.getToken());

        assertThat(userResponse.getLogin(), equalTo(login));
        assertThat(userResponse.getId(), notNullValue());
    }
}