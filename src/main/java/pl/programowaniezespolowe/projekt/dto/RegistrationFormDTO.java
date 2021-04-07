package pl.programowaniezespolowe.projekt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.programowaniezespolowe.projekt.model.User;

@Data
@NoArgsConstructor
public class RegistrationFormDTO {

    private String username;
    private String password;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder){
        System.out.println("Accepted request to register user:");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("email: " + email);
        return new User(username, email, passwordEncoder.encode(password));
    }
}
