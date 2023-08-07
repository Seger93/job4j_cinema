package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class User {

    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "full_name", "fullName",
            "email", "email",
            "password", "password"
    );

    private  int id;
    private  String fullName;
    private  String email;
    private  String password;

    public User(int id, String fullName, String email, String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}