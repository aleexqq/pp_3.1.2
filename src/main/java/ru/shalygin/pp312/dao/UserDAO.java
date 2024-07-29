package ru.shalygin.pp312.dao;

import ru.shalygin.pp312.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
