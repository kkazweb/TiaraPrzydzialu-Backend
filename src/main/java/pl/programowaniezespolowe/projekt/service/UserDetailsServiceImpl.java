package pl.programowaniezespolowe.projekt.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.configuration.JwtUtils;
import pl.programowaniezespolowe.projekt.model.PasswordResetToken;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.model.UserDetailsImpl;
import pl.programowaniezespolowe.projekt.repository.PasswordTokenRepository;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordTokenRepository passwordTokenRepository;

    public User findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika o mailu " + email));
        return user;
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    public User findUserByPasswordToken(String token){
        PasswordResetToken myToken = passwordTokenRepository.findByToken(token).orElseThrow(() -> new NoSuchElementException("Token not existing."));
        User user = myToken.getUser();
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika o nazwie " + username));
        return UserDetailsImpl.build(user);
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika o id: " + id));
        return user;
    }

}
