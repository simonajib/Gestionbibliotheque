package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User findOne(Long uid);
    public User findByLogin(String login);
    public User saveUser(User user);

    public void delete(Long uid);

    User authenticate(String login, String password);
    List<User> userSearcher(String firstName, String lastName);


    List<User> findUsersByRole(String student);

    int countUsersByRole(String role);
}