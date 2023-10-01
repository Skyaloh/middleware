package com.credable.scoringmiddleware.service.clientregistration.request;

import java.io.Serializable;
import java.util.Objects;

public class ClientRegistrationRequest implements Serializable {
    private String url;
    private String name;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRegistrationRequest that = (ClientRegistrationRequest) o;
        return Objects.equals(url, that.url) && Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, username, password);
    }

    @Override
    public String toString() {
        return "ClientRegistrationRequest{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
