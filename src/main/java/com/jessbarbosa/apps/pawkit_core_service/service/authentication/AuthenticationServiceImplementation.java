package com.jessbarbosa.apps.pawkit_core_service.service.authentication;

import com.jessbarbosa.apps.pawkit_core_service.exception.InvalidCredentialsException;
import com.jessbarbosa.apps.pawkit_core_service.model.User;
import com.jessbarbosa.apps.pawkit_core_service.provider.JwtTokenProvider;
import com.jessbarbosa.apps.pawkit_core_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImplementation(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtTokenProvider.createToken(user);
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String logout(String token) {
        // Invalidate the token logic (e.g. add to a blacklist)
        return "User logged out successfully";
    }
}
