package com.mySwing.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.mySwin.Models.User;
import com.mySwing.Services.UserService;

public class UserController implements UserService {

    private List<User> users;

    public UserController() {
        // Initialize users or fetch them from a data source
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        // Placeholder logic to add a user
        users.add(user);
        System.out.println("User added: " + user);
    }

    @Override
    public void updateUser(User user) {
        // Placeholder logic to update a user
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                existingUser.setFullName(user.getFullName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                System.out.println("User updated: " + user);
                return;
            }
        }
        System.out.println("User not found for update: " + user);
    }
   
    @Override
    public void deleteUser(String username) {
        // Placeholder logic to delete a user
        users.removeIf(user -> user.getUsername().equals(username));
        System.out.println("User deleted with username: " + username);
    }

    @Override
    public List<User> getAllUsers() {
        // Placeholder logic to get all users
        System.out.println("Getting all users");
        return new ArrayList<>(users);
    }

    @Override
    public User getUserByUsername(String username) {
        // Placeholder logic to get a user by username
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Getting user by username: " + username + " - " + user);
                return user;
            }
        }
        System.out.println("User not found for username: " + username);
        return null;
    }
}
