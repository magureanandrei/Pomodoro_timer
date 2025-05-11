package com.example.pomodorotimerapp.Controllers;

import com.example.pomodorotimerapp.Services.AuthService;
import com.example.pomodorotimerapp.Services.PomodoroService;
import com.example.pomodorotimerapp.Services.UserService;
import com.example.pomodorotimerapp.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PomodoroController {
    private AuthService authService;
    private UserService userService;
    private PomodoroService pomodoroService;
    public PomodoroController(AuthService authService, UserService userService, PomodoroService pomodoroService) {
        this.authService = authService;
        this.userService = userService;
        this.pomodoroService = pomodoroService;
    }

    @GetMapping("/pomodoro")
    public String showPomodoroPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        session.setAttribute("workDuration", 25);
        return "pomodoro";
    }
}
