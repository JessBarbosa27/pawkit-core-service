package com.jessbarbosa.apps.pawkit_core_service.controller;

import com.jessbarbosa.apps.pawkit_core_service.model.Role;
import com.jessbarbosa.apps.pawkit_core_service.model.User;
import com.jessbarbosa.apps.pawkit_core_service.service.role.RoleService;
import com.jessbarbosa.apps.pawkit_core_service.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "User APIs")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long id) {
        User user = userService.getUserDetails(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userService.updateUserDetails(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
