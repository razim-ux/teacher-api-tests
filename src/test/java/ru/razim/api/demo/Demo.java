package ru.razim.api.demo;

public class Demo {

    public static void main(String[] args) {

        MyRequestSpecification spec = new MyRequestSpecification()
                .setBaseUrl("http://85.192.34.140:8080")
                .setContentType("application/json")
                .setAuthorization("Bearer abc123");

    }
}
