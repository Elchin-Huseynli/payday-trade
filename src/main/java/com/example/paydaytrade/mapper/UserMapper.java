package com.example.paydaytrade.mapper;

import com.example.paydaytrade.model.dto.request.AppUserRequestDto;
import com.example.paydaytrade.model.dto.response.AppUserResponseDto;
import com.example.paydaytrade.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser userDtoToUser(AppUserRequestDto appUserRequestDto);
    AppUserRequestDto userToUserDto(AppUser appUser);
    AppUserResponseDto userToUserResponseDto(AppUser appUser);

}

