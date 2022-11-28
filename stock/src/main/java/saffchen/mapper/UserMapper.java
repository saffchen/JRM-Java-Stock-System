package saffchen.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import saffchen.dto.ProductDto;
import saffchen.dto.UserDto;
import saffchen.entities.ProductEntity;
import saffchen.entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {
    UserDto userEntityToUserDto(UserEntity user);
    UserEntity userDtoToUserEntity(UserDto user);
    List<UserDto> userToUserDtoList(List<UserEntity> userEntities);
    List<UserEntity> userDtoToUserEntityList(List<UserDto> userDtos);
}