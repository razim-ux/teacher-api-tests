package ru.razim.api.assertions;

import ru.razim.api.models.AuthorizedUser;
import ru.razim.api.models.UserResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserAssertions {
    public static void assertUser(UserResponse actual, AuthorizedUser expected){
        assertThat(actual.getId(), equalTo(expected.getId()));
        assertThat(actual.getLogin(), equalTo(expected.getLogin()));
        assertThat(actual.getPass(), equalTo(expected.getPassword()));
        assertThat(actual.getGames(), notNullValue());
    }

}
