package avia.cloud.gateway.auth.service;

import avia.AuthLoginServiceGrpc;
import avia.AuthLoginServiceOuterClass;
import avia.AuthSignupServiceGrpc;
import avia.AuthSignupServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcServiceImpl {

    @GrpcClient("user-service")
    AuthSignupServiceGrpc.AuthSignupServiceBlockingStub authSignupServiceStub;
    @GrpcClient("user-service")
    AuthLoginServiceGrpc.AuthLoginServiceBlockingStub authLoginServiceStub;

    public void signup(AuthSignupServiceOuterClass.SignupRequest request) {
        authSignupServiceStub.signup(request);
    }

    public String login(AuthLoginServiceOuterClass.LoginRequest request) {
        return authLoginServiceStub.login(request).getPassword();
    }
}
