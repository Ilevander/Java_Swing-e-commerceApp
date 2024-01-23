package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.User;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String username);
    List<User> getAllUsers();
    User getUserByUsername(String username);

}

