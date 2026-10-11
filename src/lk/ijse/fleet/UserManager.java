package lk.ijse.fleet;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<SystemUser> users;

    public UserManager() {
        this.users = new ArrayList<>();
    }

    public SystemUser registerUser(String username, String password, String fullName) {
        if (findByUsername(username) != null) {
            System.out.println("Username already taken");
            return null;
        }
        SystemUser user = new SystemUser(username, password, fullName);
        users.add(user);
        System.out.println("Registration successful!");
        return user;
    }

    public SystemUser login(String username, String password) {
        for (SystemUser user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public SystemUser findByUsername(String username) {
        for (SystemUser user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}