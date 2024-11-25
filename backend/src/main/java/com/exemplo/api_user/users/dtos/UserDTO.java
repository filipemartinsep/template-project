package com.exemplo.api_user.users.dtos;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String username,
        String email,
        String role
) {
}
