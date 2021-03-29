package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.repository.UserRepository;
import pl.programowaniezespolowe.projekt.service.UserDetailsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserDetailsService userDetailsService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDetailsService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> findUserById(@PathVariable Long id){
        return userDetailsService.findById(id);
    }
}
