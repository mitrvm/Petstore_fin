package com.example.flowers_k;

import java.util.ArrayList;
import java.util.List;

// коля работать работать
// NO
// https://www.google.com/url?sa=i&url=https%3A%2F%2Fmemepedia.ru%2Fgovoryashhij-ben%2F&psig=AOvVaw2R308tzBuiH5DLtXLYUNrR&ust=1677028719323000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCNjq1vq4pf0CFQAAAAAdAAAAABAE

public class User {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public User(Integer id, String username, String firstName, String lastName, String email, String password, String phone, Integer userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }
}
