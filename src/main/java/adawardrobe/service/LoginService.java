package adawardrobe.service;

import adawardrobe.model.User;
import adawardrobe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
    public class LoginService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }
        public User userAuthentification(String login, String password) {
            User user = userRepository.findByUsernameOrMail(login, login)
                    .orElseThrow(() -> new RuntimeException("Nom d'utilisateur/Email ou mot de passe incorrect !"));
            //if(!password.equals(volunteer.getPassword())){
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Nom d'utilisateur/Email ou mot de passe incorrect !");
            } return user;

        }
}
