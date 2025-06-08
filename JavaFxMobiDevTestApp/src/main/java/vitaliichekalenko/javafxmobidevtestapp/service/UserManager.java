package vitaliichekalenko.javafxmobidevtestapp.service;

import vitaliichekalenko.javafxmobidevtestapp.model.User;

public class UserManager {
    private static final UserManager instance = new UserManager();
    private User user;

    private UserManager() {}

    public static UserManager getInstance() {
        return instance;
    }

    public void login(String name) {
        this.user = new User(name);
    }

    public User getUser() {
        return user;
    }
}
