package ru.razim.api.clients;

import ru.razim.api.models.*;
import ru.razim.api.specs.ResponseSpec;

public class UserApiClient extends BaseApiClient {

    private static final String USER_ENDPOINT = "/api/user";
    private static final String USERS_ENDPOINT = "/api/users";
    private static final String USER_ENDPOINT_GAMES = "/api/user/games";

    public UserResponse getCurrentUser(String token) {
        return getWithToken(
                USER_ENDPOINT,
                token,
                ResponseSpec.success200(),
                UserResponse.class

        );
    }

    public void getCurrentUserWithoutToken() {
        get(
                USER_ENDPOINT,
                ResponseSpec.unauthorized401(),
                Object.class
        );
    }

    public ApiResponse updateCurrentUser(String token, UpdateUserRequest body) {
        return putWithToken(
                USER_ENDPOINT,
                body,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public ApiResponse deleteCurrentUser(String token) {
        return deleteWithToken(
                USER_ENDPOINT,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public void getCurrentUserWithInvalidToken() {
        getWithTokenWithoutBody(
                USER_ENDPOINT,
                "invalid_token",
                ResponseSpec.unauthorized401WithoutBody()
        );
    }

    public void getCurrentUserWithEmptyToken() {
        getWithToken(
                USER_ENDPOINT,
                "",
                ResponseSpec.unauthorized401(),
                Object.class
        );
    }

    public Object[] getLast100Users() {
        return get(
                USERS_ENDPOINT,
                ResponseSpec.success200(),
                Object[].class
        );
    }

    public GameResponse[] getUserGames(String token) {
        return getWithToken(
                USER_ENDPOINT_GAMES,
                token,
                ResponseSpec.success200(),
                GameResponse[].class
        );
    }

    public AddGameResponse addGameToUser(String token, AddGameRequest body) {
        return postWithToken(
                USER_ENDPOINT_GAMES,
                body,
                token,
                ResponseSpec.created201(),
                AddGameResponse.class
        );
    }

    public ApiResponse updateGameDlc(
            String token,
            Long gameId,
            Dlc[] dlcs) {

        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId;

        return putWithToken(
                endpoint,
                dlcs,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public ApiResponse updateGameDlcInfo(String token, Long gameId, Dlc dlcs){
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId;

        return putWithToken(
                endpoint,
                dlcs,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public ApiResponse gameUpdateField(String token, Long gameId, UpdateGameFieldRequest body) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId + "/updateField";

        return putWithToken(
                endpoint,
                body,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public GameResponse getGameById(String token, Long gameId) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId;

        return getWithToken(
                endpoint,
                token,
                ResponseSpec.success200(),
                GameResponse.class
        );
    }

    public ApiResponse getGameByIdBadRequest(String token, Long gameId) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId;

        return getWithToken(
                endpoint,
                token,
                ResponseSpec.badRequest400(),
                ApiResponse.class
        );
    }
    public ApiResponse deleteGame (String token, Long gameId) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId;

        return deleteWithToken(
                endpoint,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }

    public ApiResponse deleteDlcGame(String token, Dlc[] body, Long gameId) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId + "/dlc";

        return deleteDlcWithToken(
                endpoint,
                body,
                token,
                ResponseSpec.success200(),
                ApiResponse.class
        );
    }
    public ApiResponse deleteDlcGameBadRequest(String token, Dlc[] body, Long gameId) {
        String endpoint = USER_ENDPOINT_GAMES + "/" + gameId + "/dlc";

        return deleteDlcWithToken(
                endpoint,
                body,
                token,
                ResponseSpec.badRequest400(),
                ApiResponse.class
        );
    }
}
