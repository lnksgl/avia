package avia.cloud.gateway.auth.mapper;

import avia.AuthSignupServiceOuterClass;
import avia.cloud.gateway.auth.annotation.IgnoreSignupProtobuff;
import avia.cloud.gateway.auth.record.SignupRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthSignupProtoMapper {

    @IgnoreSignupProtobuff
    AuthSignupServiceOuterClass.SignupRequest map(SignupRecord signupRecord);
}
