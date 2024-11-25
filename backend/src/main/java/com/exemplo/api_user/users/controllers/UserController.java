package com.exemplo.api_user.users.controllers;

import com.exemplo.api_user.users.dtos.UserDTO;
import com.exemplo.api_user.users.exceptions.EntityAlreadyExistsException;
import com.exemplo.api_user.users.exceptions.EntityDoesNotExistsException;
import com.exemplo.api_user.users.models.User;
import com.exemplo.api_user.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('admin')")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) throws EntityDoesNotExistsException {
        UserDTO userDTO = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) throws EntityAlreadyExistsException {
        UserDTO createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody User user) throws EntityDoesNotExistsException {
        UserDTO updateUser = userService.updateUser(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
