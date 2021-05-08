package meo.store.utils.mapper;

import org.mapstruct.Mapper;

import meo.store.dto.UserDto;
import meo.store.persistance.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User userToDto(UserDto userDto);

	UserDto dtoToUser(User user);

}
