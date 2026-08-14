package ru.razim.api.steps;

import io.qameta.allure.Step;
import ru.razim.api.clients.AuthApiClient;
import ru.razim.api.clients.UserApiClient;
import ru.razim.api.helpers.AuthHelper;
import ru.razim.api.models.*;

public class ApiSteps {

    private final AuthApiClient authApiClient = new AuthApiClient();
    private final UserApiClient userApiClient = new UserApiClient();

    @Step("Создать авторизованного пользователя")
    public AuthorizedUser createAuthorizedUser() {
        return AuthHelper.createAuthorizedUser();
    }

    @Step("Зарегистрировать пользователя")
    public SignupResponse signup(SignupRequest request) {
        return authApiClient.signup(request);
    }

    @Step("Попытаться зарегистрировать пользователя с некорректными данными")
    public ApiResponse signupBadRequest(SignupRequest request) {
        return authApiClient.signupWithBadRequest(request);
    }

    @Step("Авторизоваться")
    public LoginResponse login(LoginRequest request) {
        return authApiClient.login(request);
    }

    @Step("Попытаться авторизоваться с неверными данными")
    public void loginUnauthorized(LoginRequest request) {
        authApiClient.loginWithUnauthorized(request);
    }

    @Step("Получить текущего пользователя")
    public UserResponse getCurrentUser(String token) {
        return userApiClient.getCurrentUser(token);
    }

    @Step("Обновить пользователя")
    public ApiResponse updateUser(String token, UpdateUserRequest request) {
        return userApiClient.updateCurrentUser(token, request);
    }

    @Step("Удалить пользователя")
    public ApiResponse deleteUser(String token) {
        return userApiClient.deleteCurrentUser(token);
    }

    @Step("Получить список пользователей")
    public Object[] getLast100Users() {
        return userApiClient.getLast100Users();
    }

    @Step("Получить список игр пользователя")
    public GameResponse[] getUserGames(String token) {
        return userApiClient.getUserGames(token);
    }

    @Step("Добавить игру пользователю")
    public AddGameResponse addGame(AuthorizedUser user, AddGameRequest request) {
        return userApiClient.addGameToUser(user.getToken(), request);
    }

    @Step("Получить игру")
    public GameResponse getGame(AuthorizedUser user, Long gameId) {
        return userApiClient.getGameById(user.getToken(), gameId);
    }

    @Step("Обновить DLC игры")
    public ApiResponse updateDlc(
            AuthorizedUser user,
            Long gameId,
            Dlc[] dlcs
    ) {
        return userApiClient.updateGameDlc(
                user.getToken(),
                gameId,
                dlcs
        );
    }

    @Step("Обновить поле игры")
    public ApiResponse updateGameField(
            AuthorizedUser user,
            Long gameId,
            UpdateGameFieldRequest request
    ) {
        return userApiClient.gameUpdateField(
                user.getToken(),
                gameId,
                request
        );
    }

    @Step("Удалить игру")
    public ApiResponse deleteGame(
            AuthorizedUser user,
            Long gameId
    ) {
        return userApiClient.deleteGame(
                user.getToken(),
                gameId
        );
    }

    @Step("Удалить DLC игры")
    public ApiResponse deleteDlc(
            AuthorizedUser user,
            Dlc[] dlcs,
            Long gameId
    ) {
        return userApiClient.deleteDlcGame(
                user.getToken(),
                dlcs,
                gameId
        );
    }
    @Step("Получить игру с ошибкой")
    public ApiResponse getGameBadRequest(
            AuthorizedUser user,
            Long gameId
    ) {
        return userApiClient.getGameByIdBadRequest(
                user.getToken(),
                gameId
        );
    }

    @Step("Удалить DLC с ошибкой")
    public ApiResponse deleteDlcBadRequest(
            AuthorizedUser user,
            Dlc[] dlcs,
            Long gameId
    ) {
        return userApiClient.deleteDlcGameBadRequest(
                user.getToken(),
                dlcs,
                gameId
        );
    }

    @Step("Получить пользователя без токена")
    public void getCurrentUserWithoutToken() {
        userApiClient.getCurrentUserWithoutToken();
    }

    @Step("Получить пользователя с неверным токеном")
    public void getCurrentUserWithInvalidToken() {
        userApiClient.getCurrentUserWithInvalidToken();
    }

    @Step("Получить пользователя с пустым токеном")
    public void getCurrentUserWithEmptyToken() {
        userApiClient.getCurrentUserWithEmptyToken();
    }
}