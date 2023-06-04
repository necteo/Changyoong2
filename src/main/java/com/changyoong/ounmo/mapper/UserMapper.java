package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserInfoDTO toUserInfoDTO(User user) {
        return UserInfoDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .height(user.getHeight())
                .weight(user.getWeight())
                .gender(user.getGender())
                .build();
    }
}
