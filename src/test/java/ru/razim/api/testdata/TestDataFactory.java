package ru.razim.api.testdata;

import ru.razim.api.builders.SignupRequestBuilder;
import ru.razim.api.builders.UpdateUserRequestBuilder;
import ru.razim.api.helpers.TestDataGenerator;
import ru.razim.api.models.*;

public class TestDataFactory {
    public static SignupRequest validSignupRequest() {
        String login = TestDataGenerator.generateLogin();
        String password = TestDataGenerator.generatePassword();

        return new SignupRequestBuilder()
                .login(login)
                .pass(password)
                .build();
    }

    public static SignupRequest signupWithoutPassword() {
        String login = TestDataGenerator.generateLogin();

        return new SignupRequestBuilder()
                .login(login)
                .build();
    }

    public static SignupRequest signupWithoutLogin() {
        String password = TestDataGenerator.generatePassword();

        return new SignupRequestBuilder()
                .pass(password)
                .build();
    }

    public static UpdateUserRequest updateUserPasswordRequest() {
        return new UpdateUserRequestBuilder()
                .password("newPassword123")
                .build();

    }

    public static LoginRequest loginWithoutPassword() {
        String login = TestDataGenerator.generateLogin();

        return new LoginRequest(login, null);
    }

    public static AddGameRequest validAddGameRequest() {

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

        return AddGameRequest.builder()
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
    }

    public static Dlc validUpdateDlc(){

        SimilarDlc similarDlc = SimilarDlc.builder()
                .dlcNameFromAnotherGame("Need for Speed Undeground")
                .isFree(true)
                .build();

        return Dlc.builder()
                .description("Test DLC description")
                .dlcName("Test DLC")
                .isDlcFree(true)
                .price(0.0)
                .rating(5)
                .similarDlc(similarDlc)
                .build();
    }
}
