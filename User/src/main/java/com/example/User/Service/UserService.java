package com.example.User.Service;

import com.example.User.DTO.Request.UserSaveRequest;
import com.example.User.DTO.Request.UserUpdateRequest;
import com.example.User.DTO.Response.UserFindByIDResponse;
import com.example.User.DTO.Response.UserSaveResponse;
import com.example.User.Entity.Role;
import com.example.User.Entity.User;
import com.example.User.Exception.AppException;
import com.example.User.Exception.ErrolCode;
import com.example.User.Mapper.UserMapper;
import com.example.User.Repository.RoleRepository;
import com.example.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    @Transactional
    public UserSaveResponse save(UserSaveRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrolCode.USERNAME_EXITS);
        }
        User user = userMapper.toUser(request);
        Role role = roleRepository.findById(request.getRole())
                .orElseThrow(()->new AppException(ErrolCode.ROLE_NAME_NO_EXITS));

        user.setRole(role);
        User userSave = userRepository.save(user);


        return userMapper.toUserSaveResponse(userSave);
    }
    @Transactional
    public UserSaveResponse update(String userID, UserUpdateRequest request){

        User user = userRepository.findById(userID)
                .orElseThrow(()->new AppException(ErrolCode.USERNAME_NO_EXITS));

        Role role = roleRepository.findById(request.getRole())
                .orElseThrow(()->new AppException(ErrolCode.ROLE_NAME_NO_EXITS));

        user.setRole(role);
        user.setGmail(request.getGmail());
        user.setPhone(request.getPhone());
        userRepository.save(user);

        return userMapper.toUserSaveResponse(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean delete(String userID){
        if (!userRepository.existsById(userID)) {
            throw new AppException(ErrolCode.USERNAME_NO_EXITS);
        }
        userRepository.deleteById(userID);

        return false;
    }

    public UserFindByIDResponse findByName(String username){
        if(!userRepository.existsByUsername(username)){
            throw new AppException(ErrolCode.USERNAME_NO_EXITS);
        }
        return userMapper.toUserFindByIDResponse(userRepository.findByUsername(username));
    }
    public UserFindByIDResponse findById(String userID){
        return userMapper.toUserFindByIDResponse(userRepository.findById(userID)
                .orElseThrow(()->new AppException(ErrolCode.USERNAME_NO_EXITS)));
    }



}
