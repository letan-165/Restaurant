package com.example.Profile.Mapper;

import com.example.Profile.DTO.Request.ProfileRequest;
import com.example.Profile.DTO.Response.ProfileFindByIdResponse;
import com.example.Profile.Entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileRequest request);
    ProfileFindByIdResponse toProfileFindByIdResponse(Profile profile);
}
