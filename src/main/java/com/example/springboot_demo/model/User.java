package com.example.springboot_demo.model;

public class User {
    private Integer userid;
    private String username;

    public User(Integer userid, String username) {
        this.userid = userid;
        this.username = username;
    }
    // Constructor를 Overloading한 경우 Default Constructor를 명시적으로 생성해야한다.
    protected User() {
    }


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                '}';
    }
}