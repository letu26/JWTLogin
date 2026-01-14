package com.javaweb.controler;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @PostMapping("/hi")
    public String hi(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        return "Hi " + name;
    }

}
