package com.example.pomodorotimerapp.Controllers;

import com.example.pomodorotimerapp.DTO.UserDTO;
import com.example.pomodorotimerapp.Services.AuthService;
import com.example.pomodorotimerapp.Services.UserService;
import com.example.pomodorotimerapp.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {


    private AuthService authService;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model) {
        try {
            authService.registerUser(username, email, password);
            return "redirect:/account-created";
        } catch (RuntimeException e) {
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/account-created")
    public String showAccountCreated() {
        return "account-created";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            Model model, HttpSession session) {
        try {
            User user = authService.loginUser(username, password);
            model.addAttribute("user", user);
            session.setAttribute("user", user);
            return "redirect:/pomodoro";
        } catch (RuntimeException e) {
            model.addAttribute("username", username);
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/pomodoro")
    public String showPomodoroPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "pomodoro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?logout=true";
    }


}
