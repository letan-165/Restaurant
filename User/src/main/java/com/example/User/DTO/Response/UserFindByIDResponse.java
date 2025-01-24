package com.example.User.DTO.Response;

import com.example.User.Entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFindByIDResponse {
    String userID;
    String username;
    String password;
    String phone;
    String gmail;
    Role role;
}
