package org.example.typinglab.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.typinglab.dto.LoginParams;
import org.example.typinglab.dto.UserCreateParam;
import org.example.typinglab.dto.UserDTO;
import org.example.typinglab.dto.UserStatsDTO;
import org.example.typinglab.entity.User;
import org.example.typinglab.entity.UserStats;
import org.example.typinglab.repo.UserStatsRepository;
import org.example.typinglab.services.UserService;
import org.example.typinglab.services.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/typinglab/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatsService userStatsService;

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

    @GetMapping("/me")
    public UserDTO getCurrentUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            throw new RuntimeException("Not logged in");
        }
        return toDTO(user);
    }

    @GetMapping("/stats")
    public UserStatsDTO getUserStats(@RequestParam("user_id") String userId) {
        UserStats stats = userStatsService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Statistics not found for user: " + userId));
        return toStatsDTO(stats);
    }

    @PostMapping("/stats")
    public UserStatsDTO updateUserStats(@Valid @RequestBody UserStatsDTO statsDTO, @AuthenticationPrincipal User currentUser) {
        if (!currentUser.getId().equals(statsDTO.getUserId()) && !currentUser.isAdmin()) {
            throw new RuntimeException("Access denied: Can only update own stats or admin");
        }
        return userStatsService.updateUserStats(statsDTO.getUserId(), statsDTO);
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