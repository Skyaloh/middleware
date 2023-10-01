package com.credable.scoringmiddleware.service.clientregistration.request;

import java.io.Serializable;
import java.util.Objects;

public class ClientRegistrationResponse implements Serializable {
    private String url;
    private Long id;
    private String name;
    private String username;
    private String password;
    private String token;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRegistrationResponse that = (ClientRegistrationResponse) o;
        return Objects.equals(url, that.url) && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, id, name, username, password, token);
    }

    @Override
    public String toString() {
        return "ClientRegistrationResponse{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
