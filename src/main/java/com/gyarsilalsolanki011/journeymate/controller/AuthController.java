package com.gyarsilalsolanki011.journeymate.controller;

import com.gyarsilalsolanki011.journeymate.model.dto.CustomerDTO;
import com.gyarsilalsolanki011.journeymate.model.dto.LoginResponse;
import com.gyarsilalsolanki011.journeymate.service.Repository.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("I am Healthy");
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(authService.registerCustomer(customerDTO));
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestParam String email, @RequestParam String password){
        return ResponseEntity.ok(authService.loginCustomer(email, password));
    }
}
