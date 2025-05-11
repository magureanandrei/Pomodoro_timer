package com.example.pomodorotimerapp.Controllers;

import com.example.pomodorotimerapp.Services.*;
import com.example.pomodorotimerapp.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {
    private AuthService authService;
    private UserService userService;
    private SettingsService settingsService;
    private PomodoroService pomodoroService;
    private SessionService sessionService;
    public HistoryController(AuthService authService, UserService userService, SettingsService settingsService, PomodoroService pomodoroService, SessionService sessionService) {
        this.authService = authService;
        this.userService = userService;
        this.settingsService = settingsService;
        this.pomodoroService = pomodoroService;
        this.sessionService = sessionService;
    }

    @GetMapping("/history")
    public String showHistoryPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        //model.addAttribute("history", userService.getHistoryForUser(user));
        return "history";
    }
}
