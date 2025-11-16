package com.codesoft.app.service;

import com.codesoft.app.entity.User;

public interface AuthService {

    User register(User user);

    String login(String username, String password);

    User getById(Long id);

    void delete(Long id);
}
