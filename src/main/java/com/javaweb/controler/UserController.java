package com.javaweb.controler;

import com.javaweb.dto.UserDTO;
import com.javaweb.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserServices userServices;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = userServices.login(userDTO.getUsername(), userDTO.getPassword());
            // Trả về token trong response
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
