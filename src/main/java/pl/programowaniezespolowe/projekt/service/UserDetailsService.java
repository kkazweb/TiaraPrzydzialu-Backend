package pl.programowaniezespolowe.projekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.programowaniezespolowe.projekt.dto.RegistrationFormDTO;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user;
        }
        else
            throw new UsernameNotFoundException("Użytkownik " + username + " nie został znaleziony.");

    }
}
