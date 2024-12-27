package com.jessbarbosa.apps.pawkit_core_service.service.user;

import com.jessbarbosa.apps.pawkit_core_service.exception.UserNotFoundException;
import com.jessbarbosa.apps.pawkit_core_service.model.User;
import com.jessbarbosa.apps.pawkit_core_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserDetails(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User updateUserDetails(Long id, User updatedUser) {
        User user = getUserDetails(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
