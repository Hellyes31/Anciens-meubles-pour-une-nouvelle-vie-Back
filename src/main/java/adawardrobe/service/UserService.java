package adawardrobe.service;


import adawardrobe.model.User;
import adawardrobe.repository.UserRepository;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(encoded);
        }

        return userRepository.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }

    public Optional<User> updateUser(String id, User userDetails) {
        Optional<User> user = userRepository.findById(Long.parseLong(id));

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setFirstname(userDetails.getFirstname());
            existingUser.setLastname(userDetails.getLastname());
            existingUser.setMail(userDetails.getMail());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setRole(userDetails.getRole());
            existingUser.setCity(userDetails.getCity());
            existingUser.setUpdated_at(userDetails.getUpdated_at());

            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                String encoded = passwordEncoder.encode(userDetails.getPassword());
                existingUser.setPassword(encoded);
            }

            User updatedUser = userRepository.save(existingUser);
            return Optional.of(updatedUser);
        }
        return Optional.empty();
    }

    public boolean deleteUser(String id) {
        Optional<User> user = userRepository.findById(Long.parseLong(id));

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}
