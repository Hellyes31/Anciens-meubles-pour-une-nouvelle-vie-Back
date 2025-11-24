package adawardrobe.service;
import adawardrobe.model.Role;
import adawardrobe.model.User;
import adawardrobe.repository.RoleRepository;
import adawardrobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user, String roleName) {
        // Vérification username
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Ce nom d'utilisateur est déjà pris !");
        }

        // Vérification email
        if (userRepository.existsByMail(user.getMail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé !");
        }

        // Validation format
        if (!user.getUsername().matches("^[a-zA-Z0-9._-]{3,20}$")) {
            throw new IllegalArgumentException("Le nom d'utilisateur doit comporter 3 à 20 caractères alphanumériques");
        }
        if (!user.getMail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Le format de l'email est invalide !");
        }

        // Hash du mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Affectation du role
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role non trouvé : " + roleName));
        user.setRole(role);

        return userRepository.save(user);
    }
}


