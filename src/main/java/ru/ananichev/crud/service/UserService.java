package ru.ananichev.crud.service;

import ru.ananichev.crud.model.Role;
import ru.ananichev.crud.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void saveUser(User user, Set<Role> roles);

    void removeUserById(long id);

    List<User> getAllUsers();

    void updateUser(User user, Set<Role> roles);

    User getUser(long id);

    Role getRoleByName(String name);
}
