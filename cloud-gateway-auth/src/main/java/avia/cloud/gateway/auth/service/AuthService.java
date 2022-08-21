package avia.cloud.gateway.auth.service;

import avia.AuthLoginServiceOuterClass;
import avia.cloud.gateway.auth.mapper.AuthSignupProtoMapper;
import avia.cloud.gateway.auth.record.LoginRecord;
import avia.cloud.gateway.auth.record.SignupRecord;
import avia.cloud.gateway.auth.util.JwtProvider;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthService {

    JwtProvider jwtProvider;
    GrpcServiceImpl grpcService;
    AuthSignupProtoMapper signupProtoMapper;

    public void signup(SignupRecord signupRecord) {
        grpcService.signup(signupProtoMapper.map(signupRecord)
                .toBuilder()
                .setPassword(jwtProvider.encodePassword(signupRecord.password()))
                .build());
    }

    public String login(LoginRecord loginRecord) {
        String password = grpcService.login(AuthLoginServiceOuterClass.LoginRequest
                .newBuilder()
                .setUsername(loginRecord.username())
                .build());
        if (jwtProvider.validatePassword(loginRecord.password(), password)) {
            return jwtProvider.generateToken(loginRecord.username());
        } else {
            throw new StatusRuntimeException(Status.INVALID_ARGUMENT.withDescription("Password."));
        }
    }

    public boolean validateToken(String token) {
        return jwtProvider.validateToken(token);
    }
}
