package com.jessbarbosa.apps.pawkit_core_service.service.authentication;

import com.jessbarbosa.apps.pawkit_core_service.model.User;

public interface AuthenticationService {

    public String login(String username, String password);

    public String register(User user);

    public String logout(String token);

}
