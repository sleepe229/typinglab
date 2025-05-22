package org.example.typinglab.services;

import jakarta.annotation.PostConstruct;
import org.example.typinglab.entity.User;
import org.example.typinglab.entity.UserStats;
import org.example.typinglab.repo.UserRepository;
import org.example.typinglab.repo.UserStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public User createUser(String login, String password) {
        if (login == null || login.isBlank()) {
            throw new RuntimeException("Login cannot be empty");
        }
        if (password == null || password.isBlank()) {
            throw new RuntimeException("Password cannot be empty");
        }
        if (userRepository.findByLogin(login) != null) {
            throw new RuntimeException("Login already exists");
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setAdmin(false);
        user.setFirstName(null);
        user.setLastName(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setBirthdate(null);
        user.setCountry(null);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Transactional
    public User login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid login or password");
    }

    @PostConstruct
    @Transactional
    public void initializeAdminUser() {
        if (userRepository.findByLogin("sysadm") == null) {
            User admin = new User();
            admin.setLogin("sysadm");
            admin.setPassword(passwordEncoder.encode("sysadm"));
            admin.setAdmin(true);
            admin.setFirstName("System");
            admin.setLastName("Admin");
            admin.setUsername("sysadm");
            admin.setEmail("sysadm@example.com");
            admin.setBirthdate(LocalDate.of(1970, 1, 1));
            admin.setCountry("Unknown");
            userRepository.save(admin);
        }
    }
}