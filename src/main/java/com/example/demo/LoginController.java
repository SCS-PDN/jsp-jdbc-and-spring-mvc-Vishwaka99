package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // GET: Show Login Page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // POST: Validate Login Credentials
    @PostMapping("/login")
    public String validateLogin(@RequestParam("email") String email, 
                                @RequestParam("password") String password,
                                Model model) {
        String sql = "SELECT COUNT(*) FROM students WHERE email = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);

        if (count > 0) {
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
}
