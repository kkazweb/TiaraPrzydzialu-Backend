package pl.programowaniezespolowe.projekt.repository;

import org.springframework.data.repository.CrudRepository;
import pl.programowaniezespolowe.projekt.model.PasswordResetToken;
import pl.programowaniezespolowe.projekt.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PasswordTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    List<PasswordResetToken> findPasswordResetTokensByUser(User user);

    Optional<PasswordResetToken> findByToken(String token);

    Optional<User> findUserByToken(String token);

    List<PasswordResetToken> findPasswordResetTokensByExpiryDateBefore(Date date);

}
