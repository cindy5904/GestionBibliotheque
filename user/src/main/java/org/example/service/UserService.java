package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User entity = userRepository.findById(id);
        if (entity != null) {
            entity.setName(user.getName());
            entity.setUsername(user.getUsername());
            entity.setPassword(user.getPassword());
        }
        return entity;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
