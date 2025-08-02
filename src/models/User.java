package models;

import java.util.UUID;

public class User {
    private final String userId;
    private String name;
    private String email;

    public User(String name, String email) {
        this.userId = name + UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "name= " + name;
    }
}
