package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.user.Users;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserInfoDTO toUserInfoDTO(Users users) {
        return UserInfoDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .username(users.getUsername())
                .password(users.getPassword())
                .nickname(users.getNickname())
                .birth(users.getBirth())
                .height(users.getHeight())
                .weight(users.getWeight())
                .gender(users.getGender())
                .build();
    }
}
