package com.example.pomodorotimerapp.Controllers;

import com.example.pomodorotimerapp.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SettingsController {
    private AuthService authService;
    private UserService userService;
    private SettingsService settingsService;
    private PomodoroService pomodoroService;
    private SessionService sessionService;
    public SettingsController(AuthService authService, UserService userService, SettingsService settingsService, PomodoroService pomodoroService, SessionService sessionService) {
        this.authService = authService;
        this.userService = userService;
        this.settingsService = settingsService;
        this.pomodoroService = pomodoroService;
        this.sessionService = sessionService;
    }

    @GetMapping("/settings")
    public String showSettingsPage(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "settings";
    }
}
