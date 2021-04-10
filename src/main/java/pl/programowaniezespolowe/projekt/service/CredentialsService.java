package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.payload.request.LoginRequest;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

@Service
public class CredentialsService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean changePassword(Long userId, String passwordOld, String passwordNew) throws Exception {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with ID: " + userId + " not found - CredentialsService"));
        if(passwordEncoder.matches(passwordOld, user1.getPassword())){
            user1.setPassword(passwordEncoder.encode(passwordNew));
            userRepository.save(user1);
            return true;
        }
        else{
            throw new Exception("Passwords do not match.");
        }
    }

}
