package pl.programowaniezespolowe.projekt.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.configuration.JwtUtils;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.model.UserDetailsImpl;
import pl.programowaniezespolowe.projekt.payload.request.LoginRequest;
import pl.programowaniezespolowe.projekt.payload.request.SignupRequest;
import pl.programowaniezespolowe.projekt.payload.response.JwtResponse;
import pl.programowaniezespolowe.projekt.payload.response.MessageResponse;
import pl.programowaniezespolowe.projekt.repository.UserRepository;
import pl.programowaniezespolowe.projekt.service.LoginService;

import javax.validation.Valid;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return loginService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        return loginService.registerUser(signupRequest);
    }
}
