package com.example.User.DTO.Request;

import com.example.User.Entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSaveRequest {
    String username;
    String password;
    String role;

    String phone;
    String gmail;
}
