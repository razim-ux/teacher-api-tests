package ru.razim.api.assertions;

import ru.razim.api.models.Info;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiAssertions {

    public static void assertInfo(
            Info actualInfo,
            String expectedStatus,
            String expectedMessage) {

        assertThat(actualInfo.getStatus(), equalTo(expectedStatus));
        assertThat(actualInfo.getMessage(), equalTo(expectedMessage));
    }
}
