package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.model.User;
import pl.programowaniezespolowe.projekt.service.UserDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDetailsService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable Long id){
        return userDetailsService.findById(id);
    }
}
