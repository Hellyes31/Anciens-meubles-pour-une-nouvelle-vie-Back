package adawardrobe.controller;


import adawardrobe.model.Login;
import adawardrobe.model.User;
import adawardrobe.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final LoginService loginService;

    public LoginController (LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Login request){
        try {
            User user = loginService.userAuthentification(request.getUsername(), request.getPassword());
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
