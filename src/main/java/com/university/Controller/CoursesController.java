package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class CoursesController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    @GetMapping("/courses")
    public String showCourses(Model model) {
        String sql = "SELECT * FROM courses";
        List<Map<String, Object>> courses = jdbcTemplate.queryForList(sql);
        model.addAttribute("courses", courses);
        return "courses";
    }

    
    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        
        Integer studentId = (Integer) session.getAttribute("studentId");

        if (studentId == null) {
            model.addAttribute("error", "You must be logged in to register for a course.");
            return "login";
        }

       
        String sql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, studentId, courseId, LocalDate.now());

        model.addAttribute("success", "You have successfully registered for the course.");
        return "redirect:/courses";
    }
}
