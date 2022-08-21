package avia.user.mapper;

import avia.AuthSignupServiceOuterClass;
import avia.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthSignupProtoMapper {

    default User map(AuthSignupServiceOuterClass.SignupRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}
