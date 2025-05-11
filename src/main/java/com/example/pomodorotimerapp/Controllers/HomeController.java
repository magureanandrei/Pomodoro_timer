package com.example.pomodorotimerapp.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(@RequestParam(value="logout", required = false)String logout,Model model) {
        if ("true".equals(logout)) {
            model.addAttribute("message", "You have been logged out.");
        }
        model.addAttribute("pageTitle", "Pomodoro Timer App");
        return "index";
    }

    @GetMapping("/error")
    public String Showerror() {
        return "error";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?logout=true";
    }

}
