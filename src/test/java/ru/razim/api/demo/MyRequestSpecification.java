package ru.razim.api.demo;

public class MyRequestSpecification {
    private String baseUrl;
    private String contentType;
    private String authorization;

    public MyRequestSpecification setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public MyRequestSpecification setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public MyRequestSpecification setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }
}

