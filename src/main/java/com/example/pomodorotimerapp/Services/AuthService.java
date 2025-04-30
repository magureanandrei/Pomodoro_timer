package com.example.pomodorotimerapp.Services;
import com.example.pomodorotimerapp.DTO.UserDTO;
import com.example.pomodorotimerapp.models.PomodoroSession;
import com.example.pomodorotimerapp.models.User;
import com.example.pomodorotimerapp.models.UserSettings;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    private final UserService userService;
    private final SettingsService settingsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService,SettingsService settingsService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.settingsService = settingsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User registerUser(String username, String email, String password) {
        if(userService.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        if(userService.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        if(password.length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters long");
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserSettings userSettings = new UserSettings();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setSettings(userSettings);
        userSettings.setUser(user);

        return userService.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
