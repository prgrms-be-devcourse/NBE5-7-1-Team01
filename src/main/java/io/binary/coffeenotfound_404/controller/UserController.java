package io.binary.coffeenotfound_404.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// index.html 페이지
public class UserController {
    @GetMapping("/")
    public String index() {
        return "test";
    }
}
