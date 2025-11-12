package adawardrobe.controller;

import adawardrobe.model.Login;
import adawardrobe.model.User;
import adawardrobe.security.JwtUtil;
import adawardrobe.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login request) {
        try {
            // Vérifie les identifiants (via ton LoginService)
            User user = loginService.userAuthentification(request.getUsername(), request.getPassword());

            // Si OK → génère un token JWT avec username + rôle
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().getRole());

            // Ne pas renvoyer le mot de passe !
            user.setPassword(null);

            // Prépare la réponse JSON
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
