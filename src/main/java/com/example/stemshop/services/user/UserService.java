package com.example.stemshop.services.user;

import com.example.stemshop.data.enums.Role;
import com.example.stemshop.dto.request.user.UserAddressUpdateRequest;
import com.example.stemshop.dto.request.user.UserCredentialsUpdateRequest;
import com.example.stemshop.dto.response.user.UserResponse;
import com.example.stemshop.exceptions.AuthException;
import com.example.stemshop.exceptions.NotFoundException;
import com.example.stemshop.models.User;
import com.example.stemshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void updateUserCredentials(UserCredentialsUpdateRequest request) {
        final User user = getUser();
        if(request.getOldPassword() != null && request.getOldPassword().equals(user.getPassword())) user.setPassword(request.getNewPassword());
        if(request.getPhone() != null) user.setPhone(request.getPhone());
        userRepository.save(user);
    }

    public void updateUserAddress(UserAddressUpdateRequest request) {
        final User user = getUser();
        if(request.getAddress() != null) user.setAddress(request.getAddress());
        if(request.getCity() != null) user.setCity(request.getCity());
        if(request.getPostalCode() != null) user.setPostalCode(request.getPostalCode());
        if(request.getCountry() != null) user.setCountry(request.getCountry());
        userRepository.save(user);
    }

    public void setUserRole(Long userId, Role role) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setRole(role);
        userRepository.save(user);
    }

    public User getUser() {
        Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(id)
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
    }

    public UserResponse getUserResponse() {
        final User user = getUser();
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFullName(user.getFullName());
        userResponse.setPhone(user.getPhone());
        userResponse.setAddress(user.getAddress());
        userResponse.setCity(user.getCity());
        userResponse.setPostalCode(user.getPostalCode());
        userResponse.setCountry(user.getCountry());
        return userResponse;
    }
}
