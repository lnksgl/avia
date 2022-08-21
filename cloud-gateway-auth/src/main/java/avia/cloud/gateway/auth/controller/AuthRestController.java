package avia.cloud.gateway.auth.controller;

import avia.cloud.gateway.auth.mapper.AuthSignupProtoMapper;
import avia.cloud.gateway.auth.record.LoginRecord;
import avia.cloud.gateway.auth.record.SignupRecord;
import avia.cloud.gateway.auth.service.AuthService;
import avia.cloud.gateway.auth.service.GrpcServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AuthRestController {

    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRecord signupRecord) {
        authService.signup(signupRecord);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRecord loginRecord) {
        return ResponseEntity.ok(authService.login(loginRecord));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        return ResponseEntity.ok(authService.validateToken(token));
    }
}
