package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.UserRepository;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {

        userRepository = theUserRepository;
    }

    public List<User> findAll(){

        return (List<User>) userRepository.findAll();
    }

    // DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
    @Override
    public User findOne(Long uid) {
        Optional<User> user = userRepository.findById(uid);
        if (user.isPresent())
            return user.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
    }


    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User authenticate(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user!=null)
            return user;
        else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login / Password are invalid");
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
    @Override
    public List<User> userSearcher(String firstName, String lastName){
        if (firstName != null && lastName != null) return getByFullName(firstName, lastName);
        else if (firstName == null && lastName != null) return getByLastName(lastName);
        else if (firstName != null && lastName == null) return getByFirstName(firstName);
        else return new ArrayList<User>();
    }

    @Override
    public List<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public int countUsersByRole(String role) {
        return userRepository.countUsersByRole(role);
    }

    public List<User> getByFirstName(String firstName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }
    public List<User> getByLastName(String lastName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if(user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }

    public List<User> getByFullName(String firstName, String lastName){
        List<User> users = new ArrayList<User>();
        for (User user : userRepository.findAll()) {
            if (user.getFirstName().toLowerCase().contains(firstName.toLowerCase()) &&
                    user.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                users.add(user);
            }
        }
        return users;
    }
    @Override
    public void delete(Long uid) {
        userRepository.deleteById(uid);
    }

}