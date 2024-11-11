package com.syarifulanam.moneymap.service;

import com.syarifulanam.moneymap.exceptions.ItemAlreadyExistsException;
import com.syarifulanam.moneymap.exceptions.ResourceNotFoundException;
import com.syarifulanam.moneymap.repository.UserRepository;
import com.syarifulanam.moneymap.dto.UserModel;
import com.syarifulanam.moneymap.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        throw new RuntimeException("Data tidak ditemukan");
    }

    public User updateUser(Long userId, User user) {
        User existingUser = getUserById(userId);

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        User existingUser = getUserById(userId);
        userRepository.deleteById(existingUser.getId());
    }

    public User createUser(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistsException("User is already registered with email " + userModel.getEmail());
        }
        User newUser = new User();
        BeanUtils.copyProperties(userModel, newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }

        throw new ResourceNotFoundException("User not found");
    }
}
