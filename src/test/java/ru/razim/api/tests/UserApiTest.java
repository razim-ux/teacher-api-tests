package ru.razim.api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.razim.api.assertions.ApiAssertions;
import ru.razim.api.assertions.UserAssertions;
import ru.razim.api.constants.ApiMessages;
import ru.razim.api.models.*;
import ru.razim.api.steps.ApiSteps;
import ru.razim.api.testdata.TestDataFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest {
    private final ApiSteps apiSteps = new ApiSteps();


    @Test
    @DisplayName("Получение последних 100 пользователей")
    @Description("Проверка получения списка последних пользователей")
    @Severity(SeverityLevel.NORMAL)
    public void getLast100UsersTest() {

        Object[] users = apiSteps.getLast100Users();

        assertThat(users, notNullValue());
        assertThat(users.length, equalTo(100));
    }

    @Test
    @DisplayName("Получение текущего пользователя")
    @Description("Проверка получения информации об авторизованном пользователе")
    @Severity(SeverityLevel.CRITICAL)
    public void getUserWithAuthTest() {

        AuthorizedUser user = apiSteps.createAuthorizedUser();

        UserResponse response =
                apiSteps.getCurrentUser(user.getToken());

        UserAssertions.assertUser(response, user);
    }

    @Test
    @DisplayName("Авторизация зарегистрированного пользователя")
    @Description("Проверка успешной авторизации")
    @Severity(SeverityLevel.CRITICAL)
    public void loginRegisteredUserTest() {

        AuthorizedUser user = apiSteps.createAuthorizedUser();

        LoginRequest login =
                new LoginRequest(
                        user.getLogin(),
                        user.getPassword()
                );

        LoginResponse response =
                apiSteps.login(login);

        assertThat(response.getToken(), notNullValue());
    }

    @Test
    @DisplayName("Изменение пароля пользователя")
    @Description("Проверка успешного изменения пароля")
    @Severity(SeverityLevel.CRITICAL)
    public void updateCurrentUserTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        UpdateUserRequest body =
                TestDataFactory.updateUserPasswordRequest();

        ApiResponse response =
                apiSteps.updateUser(
                        user.getToken(),
                        body
                );

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.USER_PASSWORD_SUCCESSFULLY_CHANGED
        );
    }

    @Test
    @DisplayName("Удаление пользователя")
    @Description("Проверка успешного удаления пользователя")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteCurrentUserTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        ApiResponse response =
                apiSteps.deleteUser(user.getToken());

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.USER_SUCCESSFULLY_DELETED
        );
    }

    @Test
    @DisplayName("Авторизация с новым паролем")
    @Description("Проверка входа после смены пароля")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithNewPasswordAfterUpdateTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        String oldPassword = user.getPassword();

        UpdateUserRequest request =
                TestDataFactory.updateUserPasswordRequest();

        ApiResponse updateResponse =
                apiSteps.updateUser(
                        user.getToken(),
                        request
                );

        ApiAssertions.assertInfo(
                updateResponse.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.USER_PASSWORD_SUCCESSFULLY_CHANGED
        );

        LoginRequest newPassLoginRequest =
                new LoginRequest(
                        user.getLogin(),
                        request.getPassword()
                );

        LoginResponse responseNew =
                apiSteps.login(newPassLoginRequest);

        assertThat(responseNew.getToken(), notNullValue());

        LoginRequest oldPassLoginRequest =
                new LoginRequest(
                        user.getLogin(),
                        oldPassword
                );

        apiSteps.loginUnauthorized(oldPassLoginRequest);
    }

    @Test
    @DisplayName("Получение списка игр пользователя")
    @Description("Проверка получения списка игр")
    @Severity(SeverityLevel.NORMAL)
    public void getUserGames() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        GameResponse[] response =
                apiSteps.getUserGames(user.getToken());

        assertThat(response, arrayWithSize(0));
    }


    @Test
    @DisplayName("Добавление игры пользователю")
    @Description("Проверка успешного добавления игры")
    @Severity(SeverityLevel.CRITICAL)
    public void addUserToGame() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        SimilarDlc similarDlc = SimilarDlc.builder()
                .dlcNameFromAnotherGame("Need for Speed Heat")
                .isFree(true)
                .build();

        Dlc dlc = Dlc.builder()
                .description("Test DLC description")
                .dlcName("Test DLC")
                .isDlcFree(true)
                .price(0.0)
                .rating(5)
                .similarDlc(similarDlc)
                .build();

        Requirements requirements = Requirements.builder()
                .hardDrive(100)
                .osName("Windows 11")
                .ramGb(16)
                .videoCard("RTX 3060")
                .build();

        AddGameRequest request = AddGameRequest.builder()
                .company("EA GAMES")
                .description("Test game description")
                .dlcs(new Dlc[]{dlc})
                .gameId(0L)
                .genre("racing")
                .isFree(false)
                .price(600.0)
                .publishDate("2026-07-31T09:18:55.031Z")
                .rating(5)
                .requiredAge(true)
                .requirements(requirements)
                .tags(new String[]{"racing", "cars"})
                .title("Need for Speed Test")
                .build();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        assertThat(response.getRegisterData(), notNullValue());
        assertThat(response.getRegisterData().getGameId(), notNullValue());

        assertThat(
                response.getRegisterData().getTitle(),
                equalTo(request.getTitle())
        );

        assertThat(
                response.getRegisterData().getCompany(),
                equalTo(request.getCompany())
        );

        assertThat(
                response.getRegisterData().getGenre(),
                equalTo(request.getGenre())
        );

        assertThat(
                response.getRegisterData().getPrice(),
                equalTo(request.getPrice())
        );

        assertThat(
                response.getInfo().getStatus(),
                equalTo("success")
        );

        assertThat(
                response.getInfo().getMessage(),
                equalTo("Game created")
        );
    }

    @Test
    @DisplayName("Обновление DLC игры")
    @Description("Проверка успешного обновления DLC игры")
    @Severity(SeverityLevel.CRITICAL)
    public void updateDlcGame() {

        AuthorizedUser user = apiSteps.createAuthorizedUser();

        AddGameRequest request = TestDataFactory.validAddGameRequest();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        SimilarDlc similarDlc = SimilarDlc.builder()
                .dlcNameFromAnotherGame("Need for Speed Heat11")
                .isFree(true)
                .build();

        Dlc dlc = Dlc.builder()
                .description("Test DLC description")
                .dlcName("Updated Test DLC")
                .isDlcFree(true)
                .price(500.0)
                .rating(10)
                .similarDlc(similarDlc)
                .build();

        Dlc[] dlcs = {dlc};

        ApiResponse responseNew =
                apiSteps.updateDlc(
                        user,
                        response.getRegisterData().getGameId(),
                        dlcs
                );

        GameResponse gameResponse =
                apiSteps.getGame(
                        user,
                        response.getRegisterData().getGameId()
                );

        ApiAssertions.assertInfo(
                responseNew.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.GAME_UPLOAD_DLC
        );

        Dlc actualDlc = null;

        for (Dlc currentDlc : gameResponse.getDlcs()) {
            if (currentDlc.getDlcName().equals(dlc.getDlcName())) {
                actualDlc = currentDlc;
                break;
            }
        }

        assertThat(actualDlc, notNullValue());
        assertThat(actualDlc.getPrice(), equalTo(dlc.getPrice()));
        assertThat(actualDlc.getRating(), equalTo(dlc.getRating()));

        assertThat(
                actualDlc.getSimilarDlc().getDlcNameFromAnotherGame(),
                equalTo(
                        dlc.getSimilarDlc().getDlcNameFromAnotherGame()
                )
        );
    }

    @Test
    @DisplayName("Обновление поля игры")
    @Description("Проверка успешного обновления одного поля игры")
    @Severity(SeverityLevel.CRITICAL)
    public void updateGameField() {

        AuthorizedUser user = apiSteps.createAuthorizedUser();

        AddGameRequest request =
                TestDataFactory.validAddGameRequest();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        UpdateGameFieldRequest body =
                UpdateGameFieldRequest.builder()
                        .fieldName("price")
                        .value(399.0)
                        .build();

        ApiResponse updateResponse =
                apiSteps.updateGameField(
                        user,
                        response.getRegisterData().getGameId(),
                        body
                );

        GameResponse updatedGame =
                apiSteps.getGame(
                        user,
                        response.getRegisterData().getGameId()
                );

        ApiAssertions.assertInfo(
                updateResponse.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.GAME_UPLOAD_FIELD
        );

        assertThat(updatedGame.getPrice(), equalTo(399.0));
    }

    @Test
    @DisplayName("Удаление игры")
    @Description("Проверка успешного удаления игры")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteGameTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        AddGameRequest request =
                TestDataFactory.validAddGameRequest();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        Long gameId =
                response.getRegisterData().getGameId();

        assertThat(gameId, notNullValue());

        ApiResponse deleteResponse =
                apiSteps.deleteGame(user, gameId);

        ApiAssertions.assertInfo(
                deleteResponse.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.GAME_DELETED
        );

        ApiResponse getDeletedGameResponse =
                apiSteps.getGameBadRequest(
                        user,
                        gameId
                );

        ApiAssertions.assertInfo(
                getDeletedGameResponse.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.GAME_DELETED_ID
        );
    }

    @Test
    @DisplayName("Удаление DLC игры")
    @Description("Проверка успешного удаления DLC")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteDlcTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        AddGameRequest request =
                TestDataFactory.validAddGameRequest();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        assertThat(response.getRegisterData(), notNullValue());
        assertThat(response.getRegisterData().getGameId(), notNullValue());

        Dlc[] dlcs = request.getDlcs();

        ApiResponse deleteDlcResponse =
                apiSteps.deleteDlc(
                        user,
                        dlcs,
                        response.getRegisterData().getGameId()
                );

        ApiAssertions.assertInfo(
                deleteDlcResponse.getInfo(),
                ApiMessages.SUCCESS,
                ApiMessages.GAME_DELETED_DLC
        );

        GameResponse gameResponse =
                apiSteps.getGame(
                        user,
                        response.getRegisterData().getGameId()
                );

        assertThat(gameResponse, notNullValue());
        assertThat(gameResponse.getDlcs().length, equalTo(0));
    }

    @Test
    @DisplayName("Удаление пустого списка DLC")
    @Description("Проверка ошибки при передаче пустого массива DLC")
    @Severity(SeverityLevel.NORMAL)
    public void deleteDlcWithEmptyListTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        AddGameRequest request =
                TestDataFactory.validAddGameRequest();

        AddGameResponse response =
                apiSteps.addGame(user, request);

        Long gameId = response.getRegisterData().getGameId();

        assertThat(gameId, notNullValue());

        Dlc[] emptyDlcs = new Dlc[0];

        ApiResponse errorResponse =
                apiSteps.deleteDlcBadRequest(
                        user,
                        emptyDlcs,
                        gameId
                );

        ApiAssertions.assertInfo(
                errorResponse.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.DLC_LIST_EMPTY
        );
    }

    @Test
    @DisplayName("Удаление DLC у несуществующей игры")
    @Description("Проверка ошибки при удалении DLC у несуществующей игры")
    @Severity(SeverityLevel.NORMAL)
    public void deleteDlcFromNonExistingGameTest() {

        AuthorizedUser user =
                apiSteps.createAuthorizedUser();

        SimilarDlc similarDlc = SimilarDlc.builder()
                .dlcNameFromAnotherGame("Need for Speed Heat11")
                .isFree(true)
                .build();

        Dlc dlc = Dlc.builder()
                .description("Test DLC description")
                .dlcName("Updated Test DLC")
                .isDlcFree(true)
                .price(500.0)
                .rating(10)
                .similarDlc(similarDlc)
                .build();

        Dlc[] dlcs = {dlc};

        Long nonExistingGameId = 999999999L;

        ApiResponse response =
                apiSteps.deleteDlcBadRequest(
                        user,
                        dlcs,
                        nonExistingGameId
                );

        ApiAssertions.assertInfo(
                response.getInfo(),
                ApiMessages.FAIL,
                ApiMessages.DLC_BAD_ID
        );
    }

    @Test
    @DisplayName("Получение пользователя без токена")
    @Description("Проверка ответа без Authorization Token")
    @Severity(SeverityLevel.NORMAL)
    public void getUserWithoutTokenTest() {
        apiSteps.getCurrentUserWithoutToken();
    }

    @Test
    @DisplayName("Получение пользователя с неверным токеном")
    @Description("Проверка ответа с некорректным JWT Token")
    @Severity(SeverityLevel.NORMAL)
    public void getUserWithInvalidTokenTest() {
        apiSteps.getCurrentUserWithInvalidToken();
    }

    @Test
    @DisplayName("Получение пользователя с пустым токеном")
    @Description("Проверка ответа с пустым JWT Token")
    @Severity(SeverityLevel.NORMAL)
    public void getUserWithEmptyTokenTest() {
        apiSteps.getCurrentUserWithEmptyToken();
    }
}