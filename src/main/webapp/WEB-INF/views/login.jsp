@PostMapping("/login")
public String validateLogin(@RequestParam("email") String email, 
                            @RequestParam("password") String password,
                            Model model, HttpSession session) {
    String sql = "SELECT student_id FROM students WHERE email = ? AND password = ?";
    List<Integer> studentIds = jdbcTemplate.queryForList(sql, new Object[]{email, password}, Integer.class);

    if (!studentIds.isEmpty()) {
        session.setAttribute("studentId", studentIds.get(0)); // Set student ID in session
        return "redirect:/courses";
    } else {
        model.addAttribute("error", "Invalid email or password.");
        return "login";
    }
}
