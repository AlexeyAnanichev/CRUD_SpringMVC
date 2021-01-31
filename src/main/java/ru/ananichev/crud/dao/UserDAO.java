package ru.ananichev.crud.dao;

import ru.ananichev.crud.model.Role;
import ru.ananichev.crud.model.User;

import java.util.List;

public interface UserDAO {

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user);

    User getUser(long id);

    User getUserByName(String username);

    Role getRoleByName(String name);

    List<User> getAllRoles();
}
