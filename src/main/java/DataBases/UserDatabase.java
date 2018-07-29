package DataBases;

import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.UserNotFoundException;
import models.User;

import java.util.*;

//TODO
public class UserDatabase {

    private static UserDatabase instance;
    private Map<String, User> users;

    private UserDatabase() {
        this.users = new HashMap<>();
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public List<User> getUsersList() {
        List<User> usersAsList = new ArrayList<>();
        for (User user : this.users.values()) {
            usersAsList.add(user);
        }
//        alternative solution
//        for (Map.Entry<String, User> entry : this.users.entrySet()) {
//            usersAsList.add(entry.getValue());
//        }
        return usersAsList;
    }

    public boolean isLoginTaken(String login) {
        return users.containsKey(login);
    }

    public User getUser(String login, String password) throws UserNotFoundException {
        if (this.users.containsKey(login) &&
                this.users.get(login).getPassword().equals(password)) {
            return this.users.get(login);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void addUserToDataBase(User user) throws LoginAlreadyExistsException {
        if (this.users.containsKey(user.getLogin())) {
            throw new LoginAlreadyExistsException();
        }
        this.users.put(user.getLogin(), user);
    }


}