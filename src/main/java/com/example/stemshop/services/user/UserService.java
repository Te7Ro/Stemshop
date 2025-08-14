package com.example.stemshop.services.user;

import com.example.stemshop.exceptions.AuthException;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void updateUser(User user) {}

    public void setUserRole() {}

    public User getUser() {
        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(id)
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
    }
}
