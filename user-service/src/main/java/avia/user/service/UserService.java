package avia.user.service;

import avia.AuthLoginServiceOuterClass;
import avia.AuthSignupServiceOuterClass;
import avia.user.entity.User;
import avia.user.exception.NotFoundException;
import avia.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found. UserId: " + id));
    }

    public boolean validateUsernameAndEmail(AuthSignupServiceOuterClass.SignupRequest request) {
        return isNull(userRepository.findByUsernameAndEmail(
                request.getUsername(),
                request.getEmail()));
    }

    public String validateUsername(AuthLoginServiceOuterClass.LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        return nonNull(user) ? user.getPassword() : null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        saveUser(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
