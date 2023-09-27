package com.example.userrestapi.converter;

import com.example.userrestapi.dto.UserDTO;
import com.example.userrestapi.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDtoToUserEntityConverter implements Converter<UserDTO, UserEntity> {

    @Override
    public UserEntity convert(UserDTO source) {
        if (Objects.isNull(source)) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(source.getEmail());
        userEntity.setFirstName(source.getFirstName());
        userEntity.setLastName(source.getLastName());
        userEntity.setDateOfBirth(source.getDateOfBirth());
        return userEntity;
    }
}
