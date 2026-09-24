package lk.iise.fleet;

import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> userList = new ArrayList<User>();

    public User registerUser(String username, String password, String fullName) {
        User found = findByUsername(username);

        if (found != null) {
            System.out.println("Username already taken");
            return null;
        }

        User newUser = new User(username, password, fullName);
        userList.add(newUser);
        System.out.println("User registered successfully!");
        return newUser;
    }

    public User login(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);

            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username)) {
                return userList.get(i);
            }
        }
        return null;
    }
}