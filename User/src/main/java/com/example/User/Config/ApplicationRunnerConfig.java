package com.example.User.Config;

import com.example.User.Entity.Permission;
import com.example.User.Entity.Role;
import com.example.User.Entity.User;
import com.example.User.Repository.PermissionRepository;
import com.example.User.Repository.RoleRepository;
import com.example.User.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ApplicationRunnerConfig {
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    UserRepository userRepository;

    @Bean
    ApplicationRunner applicationRunner(){
        return args -> {
            Permission permission1 = new Permission("READ", "read data");
            Permission permission2 = new Permission("SAVE", "save data");
            List<Permission> PER_ADMIN = List.of(permission1,permission2);
            List<Permission> PER_CUS = List.of(permission1);
            if(permissionRepository.count() == 0){
                permissionRepository.saveAll(PER_ADMIN);
            }

            Role role = new Role("ADMIN","this is admin",PER_ADMIN);
            Role role2 = new Role("CUSTOMER","this is customer",PER_CUS);

            if(!roleRepository.existsById(role.getRoleName())){
                roleRepository.save(role);

            }if(!roleRepository.existsById(role2.getRoleName())){
                roleRepository.save(role2);
            }

            User user = User.builder()
                    .username("tan")
                    .password(passwordEncoder.encode("1"))
                    .role(role)
                    .gmail("tan@1")
                    .phone("0901")
                    .build();
            if (!userRepository.existsByUsername("tan")){
                userRepository.save(user);
            }
        };
    }
}
