package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLoginAndPassword(String login, String password);
    User findByLogin(String username);

    List<User> findByRole(String role);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = ?1")
    int countUsersByRole(String role);
}