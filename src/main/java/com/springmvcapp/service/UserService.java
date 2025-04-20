package com.springmvcapp.service;

import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.repo.UserModelRepository;
import com.springmvcapp.status.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserModelRepository userModelRepository;

    public UserModel findByLogin(String login) {
        return userModelRepository.findByUsername(login).orElse(null);
    }

    public UserModel findByEmail(String email) {
        return userModelRepository.findByEmail(email).orElse(null);
    }

}
