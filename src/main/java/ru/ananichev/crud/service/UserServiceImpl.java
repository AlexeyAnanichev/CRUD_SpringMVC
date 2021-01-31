package ru.ananichev.crud.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.ananichev.crud.dao.UserDAO;
import ru.ananichev.crud.model.Role;
import ru.ananichev.crud.model.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public void saveUser(User user, Set<Role> roles) {
        user.setRoles(roles);
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(User user, Set<Role> roles) {
        user.setRoles(roles);
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return userDAO.getRoleByName(name);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User $s not found", username));
        }
        return user;
    }
}
