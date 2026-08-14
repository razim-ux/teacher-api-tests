package ru.razim.api.builders;

import ru.razim.api.models.UpdateUserRequest;

public class UpdateUserRequestBuilder {

    private String password;

    public UpdateUserRequestBuilder password(String password){
        this.password = password;
        return this;
    }

    public UpdateUserRequest build(){
        return new UpdateUserRequest(password);

    }
}
