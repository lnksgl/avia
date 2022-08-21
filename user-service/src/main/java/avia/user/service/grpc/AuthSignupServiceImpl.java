package avia.user.service.grpc;

import avia.AuthSignupServiceGrpc;
import avia.AuthSignupServiceOuterClass;
import avia.user.entity.User;
import avia.user.mapper.AuthSignupProtoMapper;
import avia.user.service.CityService;
import avia.user.service.UserService;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

import static lombok.AccessLevel.PRIVATE;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthSignupServiceImpl extends AuthSignupServiceGrpc.AuthSignupServiceImplBase {

    UserService userService;
    CityService cityService;
    AuthSignupProtoMapper signupProtoMapper;

    @Override
    public void signup(AuthSignupServiceOuterClass.SignupRequest request,
                       StreamObserver<AuthSignupServiceOuterClass.SignupResponse> responseObserver) {
        if (userService.validateUsernameAndEmail(request)) {
            User user = signupProtoMapper.map(request);
            user.setCity(cityService.getCityById(request.getCity()));
            userService.saveUser(user);
            responseObserver.onNext(next());
        } else {
            responseObserver.onError(error());
        }
        responseObserver.onCompleted();
    }

    private AuthSignupServiceOuterClass.SignupResponse next() {
        return AuthSignupServiceOuterClass.SignupResponse.newBuilder()
                .setResult(true)
                .build();
    }

    private StatusRuntimeException error() {
        return Status.INVALID_ARGUMENT
                .withDescription("Username or email.")
                .asRuntimeException();
    }
}
