package com.example.eventsystem.service;

import com.example.eventsystem.model.User;
import com.example.eventsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUser() {
        return userRepository.findAll();
    }

    public User getSingleUser(Long userId) {
        return userRepository.findUserById(userId).orElseThrow();
    }


    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (! exists) {
            throw new IllegalStateException(
                    "user with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    // users/1?...
    @Transactional
    public void updateUser(Long userId, String lastName, String firstName, String password, String email) {
        // checks if user id exists
        User user = userRepository.findById(userId).orElseThrow( ()-> new IllegalStateException(
                "User with id " + userId + " does not exists. "));

        // update last name
        if (lastName != null && lastName.length() > 0 && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }

        // update first name
        if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }

        // update password
        if (password != null && password.length() > 6 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }

        // update email
        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            // checks if email already exists
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()) {
                throw new IllegalStateException("The email is already taken by another user.");
            }
            user.setEmail(email);
        }

    }
}
