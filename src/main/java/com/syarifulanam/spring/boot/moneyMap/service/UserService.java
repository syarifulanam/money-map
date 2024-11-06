package com.syarifulanam.spring.boot.moneyMap.service;

import com.syarifulanam.spring.boot.moneyMap.entity.User;
import com.syarifulanam.spring.boot.moneyMap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}
