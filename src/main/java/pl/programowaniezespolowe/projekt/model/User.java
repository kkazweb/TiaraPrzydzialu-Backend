package pl.programowaniezespolowe.projekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ElementCollection(targetClass = Long.class)
    private List<Long> quizHistoryIds;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.quizHistoryIds = new ArrayList<>();
    }

    public void addQuizHistoryId(Long id){
        quizHistoryIds.add(id);
    }

}
