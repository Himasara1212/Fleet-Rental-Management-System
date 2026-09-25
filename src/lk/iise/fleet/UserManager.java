package lk.iise.fleet;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }


    public User registerUser(String username, String password, String fullName) {
        if (findByUsername(username) != null) {
            System.out.println("Username already taken");
            return null;
        }
        User user = new User(username, password, fullName);
        users.add(user);
        System.out.println("Registration successful!");
        return user;
    }


    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}