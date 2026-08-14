package ru.razim.api.helpers;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();
    public static String generateLogin(){
        return faker.name().username();
    }
    public static String generatePassword(){
        return faker.internet().password();
    }
}
