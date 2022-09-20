package ru.shikhov.model.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.shikhov.model.Role;
import ru.shikhov.model.User;
import ru.shikhov.model.dto.UserDto;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserDtoMapper {

    public static UserDto map(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        if ( user.getId() != null ) {
            userDto.setId( user.getId() );
        }
        if ( user.getUsername() != null ) {
            userDto.setUsername( user.getUsername() );
        }
        if ( user.getEmail() != null ) {
            userDto.setEmail( user.getEmail() );
        }
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return userDto;
    }

    public static User map(UserDto dto, PasswordEncoder encoder) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        if ( dto.getPassword() != null ) {
            user.setPassword( encode( dto.getPassword(), encoder ) );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        Set<Role> set = dto.getRoles();
        if ( set != null ) {
            user.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return user;
    }

    private static String encode(String password, PasswordEncoder encoder) {
        return encoder.encode(password);
    }
}
