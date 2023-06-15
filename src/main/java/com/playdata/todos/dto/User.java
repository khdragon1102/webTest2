package com.playdata.todos.dto;

public class User {
    private Integer id;
    private String userID;
    private String password;
    private String userName;
    private String createAt;

    public User(Integer id, String userID, String password, String userName, String createAt) {
        this.id = id;
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getCreateAt() {
        return createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
