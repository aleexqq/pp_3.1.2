package ru.shalygin.pp312.service;

import ru.shalygin.pp312.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
