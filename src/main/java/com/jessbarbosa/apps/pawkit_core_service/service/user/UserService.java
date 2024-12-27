package com.jessbarbosa.apps.pawkit_core_service.service.user;

import com.jessbarbosa.apps.pawkit_core_service.model.User;

import java.util.List;

public interface UserService {

    public User getUserDetails(Long id);

    public User updateUserDetails(Long id, User updatedUser);

    public List<User> getAllUsers();
}
