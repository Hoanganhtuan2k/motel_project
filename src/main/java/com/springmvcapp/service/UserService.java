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

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserModelRepository userModelRepository;

    private static List<UserModel> users = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        UserModel user = new UserModel();
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("abc"));
        users.add(user);
    }

    public void register(UserModel user) {
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    public UserModel findByLogin(String login) {
        return users.stream().filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElse(null);
    }

    public UserDetails loadUserByUserName(String email) throws UsernameNotFoundException {
        UserModel userModel = userModelRepository.findByEmail(email);
        if (userModel != null) {
            var springUser = User.withUsername(userModel.getEmail())
                .password(userModel.getPassword())
                .roles(String.valueOf(userModel.getRole()))
                .build();
            return springUser;
        }
        return null;
    }
}
