package org.example.typinglab.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.typinglab.dto.*;
import org.example.typinglab.entity.User;
import org.example.typinglab.entity.UserStats;
import org.example.typinglab.repo.UserStatsRepository;
import org.example.typinglab.services.UserService;
import org.example.typinglab.services.UserStatsService;
import org.example.typinglab.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typinglab/users")
public class UserController {

    private final UserService userService;

    private final UserStatsService userStatsService;

    private final WordService wordService;

    public UserController(UserService userService, UserStatsService userStatsService, WordService wordService) {
        this.userService = userService;
        this.userStatsService = userStatsService;
        this.wordService = wordService;
    }

    @PostMapping("/create")
    public UserDTO createUser(@Valid @RequestBody UserCreateParam params) {
        User user = userService.createUser(
                params.getLogin(),
                params.getPassword()
        );
        return toDTO(user);
    }

    @PostMapping("/login")
    public UserDTO login(@Valid @RequestBody LoginParams params, HttpSession session) {
        User user = userService.login(params.getLogin(), params.getPassword());
        session.setAttribute("currentUser", user.getLogin());
        return toDTO(user);
    }

    @PostMapping("/me")
    public UserDTO getCurrentUser(@RequestBody UserIdRequest request) {
        int userId = request.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return toDTO(user);
    }

    @PostMapping("/stats")
    public UserStatsDTO getUserStats(@RequestBody UserIdRequest request) {
        int userId = request.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        UserStats stats = userStatsService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Statistics not found for user: " + userId));
        return toStatsDTO(stats);
    }

    @PostMapping("/stats/update")
    public UserStatsDTO updateUserStats(@Valid @RequestBody UserStatsDTO statsDTO) {
        int userId = statsDTO.getUserId();
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return userStatsService.updateUserStats(statsDTO.getUserId(), statsDTO);
    }

    @GetMapping("/words")
    public List<String> getRandomWords(@RequestParam("level") int level) {
        return wordService.getRandomWords(level);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.isAdmin(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getBirthdate(),
                user.getCountry()
        );
    }

    private UserStatsDTO toStatsDTO(UserStats stats) {
        return new UserStatsDTO(
                stats.getUserId(),
                stats.getAverageTrainingSpeed(),
                stats.getAverageTypingSpeed(),
                stats.getCompletedTrainings(),
                stats.getMaxTypingSpeed(),
                stats.getTotalCharactersTyped(),
                stats.getMissclickPercentage()
        );
    }
}