package com.jessbarbosa.apps.pawkit_core_service.controller;


import com.jessbarbosa.apps.pawkit_core_service.dto.LoginRequest;
import com.jessbarbosa.apps.pawkit_core_service.model.User;
import com.jessbarbosa.apps.pawkit_core_service.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller", description = "Authentication APIs.")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String message = authenticationService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        String message = authenticationService.logout(token);
        return ResponseEntity.ok(message);
    }
}
