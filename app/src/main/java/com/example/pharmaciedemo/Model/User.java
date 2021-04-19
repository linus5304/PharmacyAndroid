package com.example.pharmaciedemo.Model;

public class User {
    private String email;
    private String id;
    private String imageUrl;
    private String username;

    public User() {
    }

    public User(String email, String id, String imageUrl, String username) {
        this.email = email;
        this.id = id;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
