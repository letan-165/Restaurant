package com.example.Profile.Controller;

import com.example.Profile.DTO.ApiResponse;
import com.example.Profile.DTO.Request.ProfileRequest;
import com.example.Profile.DTO.Response.ProfileFindByIdResponse;
import com.example.Profile.Entity.Profile;
import com.example.Profile.Service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileController {
    ProfileService profileService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ApiResponse<List<Profile>> findAll(){
        return ApiResponse.<List<Profile>>builder()
                .result(profileService.findAll())
                .build();
    }
    @PostMapping
    ApiResponse<Boolean> save(@RequestBody ProfileRequest request){
        return ApiResponse.<Boolean>builder()
                .result(profileService.save(request))
                .build();
    }

    @PostMapping("/{userID}")
    ApiResponse<ProfileFindByIdResponse> findById(@PathVariable String userID){
        return ApiResponse.<ProfileFindByIdResponse>builder()
                .result(profileService.findById(userID))
                .build();
    }

    @DeleteMapping("/{userID}")
    ApiResponse<Boolean> deleteById(@PathVariable String userID){
        return ApiResponse.<Boolean>builder()
                .result(profileService.deleteById(userID))
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestBody ProfileRequest request){
        return ApiResponse.<Boolean>builder()
                .result(profileService.update(request))
                .build();
    }
}
