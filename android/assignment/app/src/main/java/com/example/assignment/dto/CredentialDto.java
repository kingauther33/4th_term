package com.example.assignment.dto;

public class CredentialDto {
    private String token;
    private String secretToken;

    @Override
    public String toString() {
        return "CredentialDto{" +
                "token='" + token + '\'' +
                ", secretToken='" + secretToken + '\'' +
                '}';
    }

    public CredentialDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }
}
