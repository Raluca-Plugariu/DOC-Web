package com.project.raluca.security.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}