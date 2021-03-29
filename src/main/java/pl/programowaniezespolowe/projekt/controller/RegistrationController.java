package pl.programowaniezespolowe.projekt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.programowaniezespolowe.projekt.dto.RegistrationFormDTO;
import pl.programowaniezespolowe.projekt.service.UserDetailsService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @GetMapping("/showform")
    public RegistrationFormDTO showRegistrationForm(){
        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO();
        registrationFormDTO.setEmail("testowyemail@wp.pl");
        registrationFormDTO.setPassword("przykladowehaslo");
        registrationFormDTO.setUsername("przykladowyusername");
        return registrationFormDTO;
    }

    @PostMapping("/register")
    public String processRegistration(@RequestBody RegistrationFormDTO form){
        userDetailsService.save(form.toUser(passwordEncoder));
        return "User registered";
    }
}
