package com.exemplo.api_user.users.services;

import com.exemplo.api_user.users.dtos.UserDTO;
import com.exemplo.api_user.users.dtos.UserDTOMapper;
import com.exemplo.api_user.users.exceptions.EntityAlreadyExistsException;
import com.exemplo.api_user.users.exceptions.EntityDoesNotExistsException;
import com.exemplo.api_user.users.models.User;
import com.exemplo.api_user.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) throws EntityDoesNotExistsException {

        return userRepository.findById(id)
                .map(userDTOMapper)
                .orElseThrow(() -> new EntityDoesNotExistsException(
                        "User with id [%s] does not exist.".formatted(id)
                ));
    }

    public UserDTO createUser(User user) throws EntityAlreadyExistsException {

        boolean usernameAlreadyExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (usernameAlreadyExists) {
            throw new EntityAlreadyExistsException(
                    "Username \"%s\" already exists.".formatted(user.getUsername()
                    ));
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRole(user.getRole());
        userRepository.save(newUser);

        return userDTOMapper.apply(newUser);
    }

    public UserDTO updateUser(UUID id, User user) throws EntityDoesNotExistsException {

        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistsException(
                        "User with id [%s] does not exist.".formatted(id)
                ));

        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setRole(user.getRole());
        userRepository.save(updateUser);

        return userDTOMapper.apply(updateUser);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}