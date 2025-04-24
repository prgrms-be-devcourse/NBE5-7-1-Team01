package io.binary.coffeenotfound_404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// admin.html 페이지
public class AdminController {
    @GetMapping("/admin")
    public String adminPage() {
        return "redirect:/admin.html"; // templates/admin.html
    }
}
