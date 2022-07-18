package com.activity.four.service;

import com.activity.four.model.Users;
import com.activity.four.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers() {
        return usersRepository.findAll();
    }

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    public void deleteUser(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("user with id number of " + userId + " does not exist"));
        usersRepository.deleteById(user.getId());
    }

    @Transactional
    public void updateUser(Long userId, String username, String password, String role) {
        Users user = usersRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("employee with id number of " + userId + " does not exist"));
        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)) {
            user.setUsername(username);
        }
        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (role != null && role.length() > 0 && !Objects.equals(user.getRole(), role)) {
            user.setRole(role);
        }
    }

}
