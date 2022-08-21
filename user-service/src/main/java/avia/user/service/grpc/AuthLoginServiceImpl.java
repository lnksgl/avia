package avia.user.service.grpc;

import avia.AuthLoginServiceGrpc;
import avia.AuthLoginServiceOuterClass;
import avia.AuthSignupServiceOuterClass;
import avia.user.service.UserService;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthLoginServiceImpl extends AuthLoginServiceGrpc.AuthLoginServiceImplBase {

    UserService userService;

    @Override
    public void login(AuthLoginServiceOuterClass.LoginRequest request, StreamObserver<AuthLoginServiceOuterClass.LoginResponse> responseStreamObserver) {
        String password = userService.validateUsername(request);
        if (nonNull(password)) {
            responseStreamObserver.onNext(next(password));
        } else {
            responseStreamObserver.onError(error());
        }
        responseStreamObserver.onCompleted();
    }

    private AuthLoginServiceOuterClass.LoginResponse next(String password) {
        return AuthLoginServiceOuterClass.LoginResponse.newBuilder()
                .setPassword(password)
                .build();
    }

    private StatusRuntimeException error() {
        return Status.INVALID_ARGUMENT
                .withDescription("Username.")
                .asRuntimeException();
    }
}
