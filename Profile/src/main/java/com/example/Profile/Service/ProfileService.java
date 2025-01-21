package com.example.Profile.Service;

import com.example.Profile.DTO.Request.ProfileRequest;
import com.example.Profile.DTO.Response.ProfileFindByIdResponse;
import com.example.Profile.Entity.Profile;
import com.example.Profile.Mapper.ProfileMapper;
import com.example.Profile.Repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;
    ProfileMapper profileMapper;

    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    public boolean save(ProfileRequest request){
        if (profileRepository.existsById(request.getUserID())){
            return false;
        }
        profileRepository.save(profileMapper.toProfile(request));
        return true;
    }

    public ProfileFindByIdResponse findById(String userID){
        return profileMapper.toProfileFindByIdResponse(profileRepository.findById(userID).orElseThrow());
    }

    public boolean deleteById(String userID){
        if (!profileRepository.existsById(userID)){
            return false;
        }
        profileRepository.deleteById(userID);
        return true;
    }

    public boolean update(ProfileRequest request){
        if (!profileRepository.existsById(request.getUserID())){
            return false;
        }
        profileRepository.save(profileMapper.toProfile(request));
        return true;
    }





}
