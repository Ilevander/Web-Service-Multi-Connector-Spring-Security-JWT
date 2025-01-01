package com.ilyass.wsmulticonnectorspringsecurityjwt.dtos.user;

public record CreateUserRequest(String username, String password, String email) {
}
