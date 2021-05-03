package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.programowaniezespolowe.projekt.model.PasswordResetToken;
import pl.programowaniezespolowe.projekt.model.User;

import java.util.Optional;

public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    Optional<User> findUserByToken(String token);
}
