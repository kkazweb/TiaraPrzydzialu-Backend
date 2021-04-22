package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.configuration.JwtUtils;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.payload.request.LoginRequest;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

@Service
public class CredentialsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    public boolean changePassword(Long userId, String passwordOld, String passwordNew){
        User user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with ID: " + userId + " not found - CredentialsService"));
        if(passwordEncoder.matches(passwordOld, user1.getPassword())){
            user1.setPassword(passwordEncoder.encode(passwordNew));
            userRepository.save(user1);
            return true;
        }
        else{
            try {
                throw new Exception("Passwords do not match.");
            } catch (Exception e){
                System.out.println(e.toString() + " UserID: " + userId + " passwordOld: " + passwordOld + " passwordNew:" + passwordNew);
            }
        }
        return false;
    }

    public boolean changeEmail(Long userId, String passwordOld, String emailNew){
        User user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with ID: " + userId + " not found - CredentialsService"));
        try {
            if (passwordEncoder.matches(passwordOld, user1.getPassword())) {
                try {
                    if (userRepository.existsByEmail(emailNew))
                        throw new Exception("Email is already in use.");
                } catch (Exception e) {
                    System.out.println(e.toString() + " UserID: " + userId + " email: " + emailNew);
                    return false;
                }
                user1.setEmail(emailNew);
                userRepository.save(user1);
                return true;
            }
            throw new Exception("Passwords do not match.");
        } catch (Exception e){
            System.out.println(e.toString() + " UserID: " + userId + " email: " + emailNew);
        }
        return false;
    }

    public boolean deleteAccount(Long userId, String passwordOld){
        User user1 = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User with ID: " + userId + " not found - CredentialsService"));
        if(passwordEncoder.matches(passwordOld, user1.getPassword())){
            userRepository.delete(user1);
            return true;
        }
        else{
            try {
                throw new Exception("Passwords do not match.");
            }
            catch (Exception e){
                System.out.println(e.toString() + " userId: " + userId + " passwordOld: " + passwordOld);
            }
        }
        return false;
    }
}
